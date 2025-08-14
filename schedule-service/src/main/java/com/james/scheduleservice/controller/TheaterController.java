package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/theaters")
@RestController
@RequiredArgsConstructor
public class TheaterController {
  private final TheaterFacade theaterFacade;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> createTheater(@RequestBody @Validated UpsertTheaterRequest request) {
    this.theaterFacade.createTheater(request);
    return BaseResponse.ok();
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> updateTheater(
      @PathVariable Long id, @RequestBody @Validated UpsertTheaterRequest request) {
    request.attachId(id);
    this.theaterFacade.updateTheater(request);
    return BaseResponse.ok();
  }

  @PatchMapping("/finger-foods/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public BaseResponse<Void> addFingerFood(
      @PathVariable Long id, @RequestBody AddFingerFoodRequest request) {
    request.attachTheaterId(id);
    this.theaterFacade.addFingerFood(request);
    return BaseResponse.ok();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<TheaterDetailResponse> findDetailTheaterById(@PathVariable Long id) {
    return this.theaterFacade.findDetailTheaterById(id);
  }

  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<TheaterResponse>> findByFilter(
      @Nullable TheaterCriteria criteria) {
    return this.theaterFacade.findByFilter(criteria);
  }

  @GetMapping("/rooms")
  @ResponseStatus(HttpStatus.OK)
  @Operation(tags = {"Theater APIs"})
  @SecurityRequirement(name = "Bearer Authentication")
  @PreAuthorize("isAuthenticated()")
  public BaseResponse<PaginationResponse<RoomResponse>> findRoomByFilter(
      @Nonnull RoomCriteria criteria) {
    return this.theaterFacade.findRoomByFilter(criteria);
  }

  @Hidden
  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/internal/verify-admin-theater", headers = "secret-key=movie-service")
  public Boolean validAdminTheater(@RequestBody ValidAdminTheaterRequest request) {
    return this.theaterFacade.validAdminTheater(request);
  }

  @Hidden
  @GetMapping(
      value = "/internal/{id}",
      headers = {"secret-key=user-service001", "secret-key=movie-service"})
  @ResponseStatus(HttpStatus.OK)
  public TheaterDTO findScheduleById(@PathVariable Long id) {
    return this.theaterFacade.findTheaterById(id);
  }

  @Hidden
  @PostMapping(value = "/internal/food/{id}", headers = "secret-key=booking-service")
  public Float getPriceOfFoodById(@PathVariable long id, @RequestParam("foodId") Long foodId) {
    return this.theaterFacade.getPriceOfFood(id, foodId);
  }
}
