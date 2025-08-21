package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.dto.AssetDTO;
import com.james.scheduleservice.dto.FoodDTO;
import com.james.scheduleservice.entity.FingerFood;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.FingerFoodFacade;
import com.james.scheduleservice.response.BaseResponse;
import com.james.scheduleservice.response.FoodResponse;
import com.james.scheduleservice.response.PaginationResponse;
import com.james.scheduleservice.resquest.FoodCriteria;
import com.james.scheduleservice.service.FingerFoodService;
import com.james.scheduleservice.specification.FoodSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FingerFoodFacadeImpl implements FingerFoodFacade {
  private final FingerFoodService fingerFoodService;

  @Override
  public FoodDTO findFingerFoodById(Long id) {
    var fingerFood =
        this.fingerFoodService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.FOOD_NOT_FOUND));
    return FoodDTO.builder()
        .foodType(fingerFood.getFoodType())
        .foodName(fingerFood.getFoodName())
        .price(fingerFood.getPrice())
        .id(fingerFood.getId())
        .build();
  }

  @Override
  public BaseResponse<PaginationResponse<FoodResponse>> findAllFood(FoodCriteria criteria) {
    Specification<FingerFood> specification =
        FoodSpecification.hasTheaterId(criteria.getTheaterId())
            .and(FoodSpecification.hasPrice(criteria.getPrice()))
            .and(FoodSpecification.hasFoodType(criteria.getFoodType()));
    Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());
    var pages = fingerFoodService.findAll(specification, pageable);

    var response =
        PaginationResponse.<FoodResponse>builder()
            .data(
                pages
                    .get()
                    .map(
                        fingerFood ->
                            FoodResponse.builder()
                                .id(fingerFood.getId())
                                .foodName(fingerFood.getFoodName())
                                .foodType(fingerFood.getFoodType())
                                .foodAssets(
                                    fingerFood.getFingerFoodAssets().stream()
                                        .map(
                                            fingerFoodAsset ->
                                                AssetDTO.builder()
                                                    .mediaType(fingerFoodAsset.getMediaType())
                                                    .mediaKey(fingerFoodAsset.getMediaKey())
                                                    .build())
                                        .toList())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalElements(pages.getNumberOfElements())
            .totalPages(pages.getTotalPages())
            .build();
    return BaseResponse.build(response, true);
  }
}
