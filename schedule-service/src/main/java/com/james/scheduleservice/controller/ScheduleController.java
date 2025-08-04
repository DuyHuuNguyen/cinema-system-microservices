package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.DoScheduleRequest;
import com.james.scheduleservice.resquest.ScheduleCriteria;
import com.james.scheduleservice.resquest.UpsertScheduleRequest;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {
  private final MovieScheduleFacade scheduleFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(
      tags = {"Schedule APIs"},
      summary = "You can not create schedule in now and in the past.")
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<DoScheduleResponse> doSchedule(@RequestBody DoScheduleRequest request) {
    return this.scheduleFacade.doSchedules(request);
  }

  @PostMapping("/schedule")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Schedule APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> doSchedule(@RequestBody UpsertScheduleRequest request) {
    this.scheduleFacade.doSchedule(request);
    return BaseResponse.ok();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Schedule APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> deleteScheduleById(
      @PathVariable Long id, @RequestParam Long theaterId) {
    this.scheduleFacade.deleteScheduleById(id, theaterId);
    return BaseResponse.ok();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Schedule APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<ScheduleDetailResponse> findDetailScheduleById(@PathVariable Long id) {
    return this.scheduleFacade.findDetailScheduleById(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Schedule APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<ScheduleResponse>> findByFilter(
      @NonNull ScheduleCriteria criteria) {
    return this.scheduleFacade.findByFilter(criteria);
  }

  @Hidden
  @GetMapping(value = "/internal/{id}", headers = "secret-key=user-service001")
  @ResponseStatus(HttpStatus.OK)
  public ScheduleDTO findScheduleById(@PathVariable Long id) {
    return this.scheduleFacade.findScheduleById(id);
  }
}
