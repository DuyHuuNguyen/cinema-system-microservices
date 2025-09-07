package com.james.paymentservice.facade.impl;

import com.james.paymentservice.config.SecurityUserDetails;
import com.james.paymentservice.dto.TransactionCreateDTO;
import com.james.paymentservice.dto.TransactionDTO;
import com.james.paymentservice.dto.WalletDTO;
import com.james.paymentservice.entity.Transaction;
import com.james.paymentservice.entity.Wallet;
import com.james.paymentservice.enums.ErrorCode;
import com.james.paymentservice.enums.TransactionEnum;
import com.james.paymentservice.enums.TransactionType;
import com.james.paymentservice.exception.DoubleSpendingException;
import com.james.paymentservice.exception.EntityNotFoundException;
import com.james.paymentservice.facade.TransactionFacade;
import com.james.paymentservice.response.*;
import com.james.paymentservice.resquest.CreateTransactionInternalForBookingTicket;
import com.james.paymentservice.resquest.CreateTransactionRequest;
import com.james.paymentservice.resquest.SpendingTimeRangeRequest;
import com.james.paymentservice.resquest.TransactionCriteria;
import com.james.paymentservice.service.*;
import com.james.paymentservice.specification.TransactionSpecification;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {
  private final TransactionService transactionService;
  private final IdempotencyService idempotencyService;
  private final UserService userService;
  private final BookingService bookingService;
  private final WalletService walletService;

  private final CheckinPaymentCreatedProducer checkinPaymentCreatedProducer;

  @Override
  public void createTransaction(CreateTransactionRequest createTransactionRequest) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var isDuplicationSpendingRequest =
        idempotencyService.hasIdempotencyKey(createTransactionRequest.getIdempotencyKey());
    if (isDuplicationSpendingRequest) throw new DoubleSpendingException(ErrorCode.DOUBLE_SPENDING);

    var transactionCreateDTO =
        TransactionCreateDTO.builder()
            .principalId(principal.getId())
            .amount(createTransactionRequest.getAmount())
            .transactionType(createTransactionRequest.getTransactionType())
            .destinationWalletId(createTransactionRequest.getPartnerId())
            .sourceWalletId(createTransactionRequest.getWalletId())
            .build();
    this.checkinPaymentCreatedProducer.createTransaction(transactionCreateDTO);
    log.info("Create transaction");
  }

  @Override
  public BaseResponse<TransactionDetailResponse> findTransactionDetailById(Long id) {
    var transaction =
        this.transactionService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.TRANSACTION_NOT_FOUND));
    var sourceWallet = transaction.getSourceWallet();
    var sourceWalletDTO =
        WalletDTO.builder()
            .ownerId(sourceWallet.getUserId())
            .walletName(sourceWallet.getWalletName())
            .id(sourceWallet.getId())
            .build();

    var destinationWallet = transaction.getDestinationWallet();
    var destinationWalletDTO =
        WalletDTO.builder()
            .id(destinationWallet.getId())
            .ownerId(destinationWallet.getUserId())
            .walletName(destinationWallet.getWalletName())
            .build();
    var transactionDetailResponse =
        TransactionDetailResponse.builder()
            .id(transaction.getId())
            .amount(transaction.getAmount())
            .createdAt(transaction.getCreatedAt())
            .transactionStatus(transaction.getStatus())
            .transactionType(transaction.getType())
            .sourceWalletDTO(sourceWalletDTO)
            .destinationWalletDTO(destinationWalletDTO)
            .build();
    return BaseResponse.build(transactionDetailResponse, true);
  }

  @Override
  public BaseResponse<PaginationResponse<TransactionResponse>> findByFilter(
      TransactionCriteria transactionCriteria) {
    Specification<Transaction> transactionSpecification =
        TransactionSpecification.withCreatedAtWithin30Minutes(transactionCriteria.getCreatedAt())
            .and(
                TransactionSpecification.withDestinationWalletId(
                    transactionCriteria.getDestinationWalletId()));
    var pageable =
        PageRequest.of(transactionCriteria.getCurrentPage(), transactionCriteria.getPageSize());
    var pages = transactionService.findAll(transactionSpecification, pageable);

    var response =
        PaginationResponse.<TransactionResponse>builder()
            .data(
                pages
                    .get()
                    .map(
                        transaction ->
                            TransactionResponse.builder()
                                .id(transaction.getId())
                                .transactionStatus(transaction.getStatus())
                                .transactionType(transaction.getType())
                                .createdAt(transaction.getCreatedAt())
                                .amount(transaction.getAmount())
                                .build())
                    .toList())
            .currentPage(transactionCriteria.getCurrentPage())
            .totalElements(pages.getNumberOfElements())
            .totalPages(pages.getTotalPages())
            .build();
    return BaseResponse.build(response, true);
  }

  @Override
  public BaseResponse<SpendingAnalysisResponse> findByAnalysisTimeRange(
      SpendingTimeRangeRequest spendingTimeRangeRequest) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var wallet =
        this.walletService
            .findByUserIdAndId(principal.getId(), spendingTimeRangeRequest.getWalletId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WALLET_NOT_FOUND));

    var transactions =
        this.transactionService.findTransactionBySourceWalletIdAndTimeRange(
            spendingTimeRangeRequest.getWalletId(),
            spendingTimeRangeRequest.getFrom(),
            spendingTimeRangeRequest.getTo());
    List<Long> transactionIds = new ArrayList<>();

    double totalAmount = 0;
    int transactionSuccessfulTimes = 0;

    for (var transaction : transactions) {
      var isTransactionSuccessful = transaction.getStatus().isSuccess();

      if (isTransactionSuccessful) {
        transactionIds.add(transaction.getId());
        totalAmount += transaction.getAmount();
        transactionSuccessfulTimes++;
      }
    }
    return BaseResponse.build(
        SpendingAnalysisResponse.builder()
            .transactionTimes(transactionSuccessfulTimes)
            .transactionAmounts(totalAmount)
            .transactionIds(transactionIds)
            .build(),
        true);
  }

  @Override
  public Boolean verifyTransaction(Long id) {
    var transaction =
        this.transactionService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WALLET_NOT_FOUND));
    return true;
  }

  @Override
  public TransactionDTO findTransactionById(Long id) {
    var transaction =
        this.transactionService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WALLET_NOT_FOUND));
    return TransactionDTO.builder()
        .id(transaction.getId())
        .price(transaction.getAmount())
        .transactionStatus(transaction.getStatus())
        .transactionType(transaction.getType())
        .build();
  }

  @Override
  public Long createTransactionInternal(
      CreateTransactionInternalForBookingTicket createTransactionInternalForBookingTicket) {
    Wallet sourceWallet = null;
    Wallet destinationWallet = null;
    try {
      sourceWallet =
          this.walletService
              .findById(createTransactionInternalForBookingTicket.getSourceWalletId())
              .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));
      destinationWallet =
          this.walletService
              .findById(createTransactionInternalForBookingTicket.getDestinationWalletId())
              .orElseThrow(() -> new EntityNotFoundException(ErrorCode.PAYMENT_NOT_FOUND));

      sourceWallet.minusBalance(createTransactionInternalForBookingTicket.getAmount());
      destinationWallet.plusBalance(createTransactionInternalForBookingTicket.getAmount());
    } catch (EntityNotFoundException e) {
      log.info("Wallet could not be found {}", createTransactionInternalForBookingTicket);
      return -1L;
    }
    var transaction =
        Transaction.builder()
            .amount(createTransactionInternalForBookingTicket.getAmount())
            .type(TransactionType.TRANSFER)
            .destinationWallet(destinationWallet)
            .sourceWallet(sourceWallet)
            .status(TransactionEnum.SUCCESS)
            .build();
    var newTransaction = this.transactionService.save(transaction);
    log.info("Successfully processed transaction");

    return newTransaction.getId();
  }
}
