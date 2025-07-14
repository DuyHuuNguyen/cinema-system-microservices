package com.james.userservice.facade.impl;

import com.james.userservice.config.SecurityUserDetails;
import com.james.userservice.entity.WorkShift;
import com.james.userservice.enums.ErrorCode;
import com.james.userservice.exception.EntityNotFoundException;
import com.james.userservice.facade.WorkPlaceFacade;
import com.james.userservice.mapper.WorkShiftMapper;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationWorkShiftResponse;
import com.james.userservice.response.TheaterProfileResponse;
import com.james.userservice.response.WorkShiftResponse;
import com.james.userservice.resquest.CheckInWorkShiftRequest;
import com.james.userservice.resquest.CheckOutWorkShiftRequest;
import com.james.userservice.resquest.WorkShiftRequest;
import com.james.userservice.service.ScheduleService;
import com.james.userservice.service.WorkPlaceService;
import com.james.userservice.service.WorkShiftService;
import com.james.userservice.specification.WorkShiftSpecification;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkPlaceFacadeImpl implements WorkPlaceFacade {
  private final WorkPlaceService workPlaceService;
  private final WorkShiftService workShiftService;
  private final WorkShiftMapper workShiftMapper;
  private final ScheduleService scheduleService;

  @Override
  public BaseResponse<PaginationWorkShiftResponse<WorkShiftResponse, TheaterProfileResponse>>
      findMyWordShift(WorkShiftRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Specification<WorkShift> specification =
        WorkShiftSpecification.hasOwnerId(principal.getId())
            .and(WorkShiftSpecification.hasTheaterId(request.getTheaterId()));

    var pageable = PageRequest.of(request.getCurrentPage(), request.getPageSize());
    var workshiftPage = this.workShiftService.findAll(specification, pageable);
    var theaterProfileResponse = this.scheduleService.findTheaterById(request.getTheaterId());

    return BaseResponse.build(
        PaginationWorkShiftResponse.<WorkShiftResponse, TheaterProfileResponse>builder()
            .data(
                workshiftPage
                    .get()
                    .map(workShift -> this.workShiftMapper.toWorkPlaceResponse(workShift))
                    .toList())
            .theater(theaterProfileResponse)
            .currentPage(request.getCurrentPage())
            .totalPages(workshiftPage.getTotalPages())
            .totalElements(workshiftPage.getNumberOfElements())
            .build(),
        true);
  }

  @Override
  @Transactional
  public void checkInWorkShift(CheckInWorkShiftRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var workShift =
        this.workShiftService
            .findWorkShiftByOwnerIdAndId(principal.getId(), request.getWorkShiftId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WORK_SHIFT_NOT_FOUNT));
    var now = Instant.now().toEpochMilli();
    workShift.checkIn(now);
    this.workShiftService.save(workShift);
  }

  @Override
  @Transactional
  public void checkOutWorkShift(CheckOutWorkShiftRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var workShift =
        this.workShiftService
            .findWorkShiftByOwnerIdAndId(principal.getId(), request.getWorkShiftId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.WORK_SHIFT_NOT_FOUNT));
    var now = Instant.now().toEpochMilli();
    workShift.checkOut(now);
    this.workShiftService.save(workShift);
  }
}
