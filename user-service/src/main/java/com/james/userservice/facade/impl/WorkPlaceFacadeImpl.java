package com.james.userservice.facade.impl;

import com.james.userservice.config.SecurityUserDetails;
import com.james.userservice.entity.WorkShift;
import com.james.userservice.facade.WorkPlaceFacade;
import com.james.userservice.mapper.WorkShiftMapper;
import com.james.userservice.response.BaseResponse;
import com.james.userservice.response.PaginationWorkShiftResponse;
import com.james.userservice.response.TheaterProfileResponse;
import com.james.userservice.response.WorkShiftResponse;
import com.james.userservice.resquest.WorkShiftRequest;
import com.james.userservice.service.ScheduleService;
import com.james.userservice.service.WorkPlaceService;
import com.james.userservice.service.WorkShiftService;
import com.james.userservice.specification.WorkShiftSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
            .currentPage(1)
            .totalPages(1)
            .totalElements(1)
            .build(),
        true);
  }
}
