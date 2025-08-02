package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.dto.*;
import com.james.scheduleservice.entity.*;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.enums.RoleEnums;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.response.*;
import com.james.scheduleservice.resquest.*;
import com.james.scheduleservice.service.*;
import com.james.scheduleservice.specification.RoomSpecification;
import com.james.scheduleservice.specification.TheaterSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TheaterFacadeImpl implements TheaterFacade {
  private final TheaterService theaterService;
  private final UserService userService;
  private final TheaterRateService theaterRateService;
  private final NotificationService notificationService;
  private final RoomService roomService;

  @Override
  public TheaterDTO findTheaterById(Long id) {
    var theater =
        this.theaterService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.SCHEDULE_NOT_FOUND));
    return TheaterDTO.builder()
        .id(theater.getId())
        .theaterName(theater.getTheaterName())
        .description(theater.getDescription())
        .build();
  }

  @Override
  public Boolean validAdminTheater(ValidAdminTheaterRequest request) {
    var theater =
        this.theaterService
            .findById(request.getTheaterId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));
    var isValidAdminTheater = theater.getDirectorId().equals(request.getAdminId());
    return isValidAdminTheater;
  }

  @Override
  @Transactional
  public void createTheater(UpsertTheaterRequest request) {
    var isMissingLocation =
        request.getLocationDTO() == null
            || request.getLocationDTO().getLatitude() == null
            || request.getLocationDTO().getLongitude() == null;
    if (isMissingLocation) throw new EntityNotFoundException(ErrorCode.LOCATION_INVALID);

    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        Theater.builder()
            .theaterName(request.getTheaterName())
            .directorId(principal.getId())
            .build();

    this.addLocationIntoTheater(request.getLocationDTO(), theater);

    var isMissingTheaterAsset =
        request.getTheaterAssetDTOS() == null || request.getTheaterAssetDTOS().isEmpty();
    if (!isMissingTheaterAsset) this.addTheaterAssets(request.getTheaterAssetDTOS(), theater);

    var isMissingRoomData = request.getRoomDTOS() == null || request.getRoomDTOS().isEmpty();
    if (!isMissingRoomData) this.addRoomsIntoTheater(request.getRoomDTOS(), theater);

    var isMissFingerFoodData =
        request.getFingerFoodDTOS() == null || request.getFingerFoodDTOS().isEmpty();
    if (!isMissFingerFoodData) this.addFingerFoodIntoTheater(request.getFingerFoodDTOS(), theater);

    var newTheater = this.theaterService.saveAndFlush(theater);
    if (!principal.isAdmin()) {
      var addRoleRequest = AddRoleRequest.builder().roleEnums(RoleEnums.ADMIN).build();
      userService.addRole(principal.getId(), addRoleRequest);
    }

    var notificationNewTheaterRequest =
        NotificationNewTheaterRequest.builder()
            .theaterId(newTheater.getId())
            .description(newTheater.getDescription())
            .firstImage(newTheater.getFirstImage())
            .build();
    this.notificationService.notificationNewTheater(notificationNewTheaterRequest);
  }

  @Override
  @Transactional
  public void addFingerFood(AddFingerFoodRequest request) {
    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        theaterService
            .findTheaterByDirectorIdAndTheaterId(principal.getId(), request.getTheaterId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));

    this.addFingerFoodIntoTheater(request.getFingerFoodDTOS(), theater);
    this.theaterService.save(theater);
  }

  @Override
  public void updateTheater(UpsertTheaterRequest request) {
    var isMissingLocation =
        request.getLocationDTO() == null
            || request.getLocationDTO().getLatitude() == null
            || request.getLocationDTO().getLongitude() == null;
    if (isMissingLocation) throw new EntityNotFoundException(ErrorCode.LOCATION_INVALID);

    var principal =
        (SecurityUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    var theater =
        this.theaterService
            .findTheaterByDirectorIdAndTheaterId(principal.getId(), request.getId())
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));

    this.addLocationIntoTheater(request.getLocationDTO(), theater);

    theater.removeAllRooms();
    this.addRoomsIntoTheater(request.getRoomDTOS(), theater);

    theater.removeAllFingerFoods();
    this.addFingerFoodIntoTheater(request.getFingerFoodDTOS(), theater);

    theater.removeAllTheaterAssets();
    this.addTheaterAssets(request.getTheaterAssetDTOS(), theater);

    this.theaterService.save(theater);
  }

  @Override
  public BaseResponse<TheaterDetailResponse> findDetailTheaterById(Long id) {
    var theater =
        this.theaterService
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException(ErrorCode.THEATER_NOT_FOUND));
    var averageStarTheater = this.theaterRateService.getAverageStarByTheaterId(id);
    var theaterDetailResponse =
        TheaterDetailResponse.builder()
            .theaterName(theater.getTheaterName())
            .description(theater.getDescription())
            .locationDTO(theater.getLocationDTO())
            .theaterAssetDTOS(
                theater.getTheaterAssets().stream()
                    .map(
                        theaterAsset ->
                            AssetDTO.builder()
                                .mediaKey(theaterAsset.getMediaKey())
                                .mediaType(theaterAsset.getMediaType())
                                .build())
                    .toList())
            .averageStars(averageStarTheater)
            .build();
    return BaseResponse.build(theaterDetailResponse, true);
  }

  @Override
  public BaseResponse<PaginationResponse<TheaterResponse>> findByFilter(TheaterCriteria criteria) {
    Specification<Theater> specification =
        TheaterSpecification.hasTheaterName(criteria.getName())
            .and(TheaterSpecification.hasLocation(criteria.getLocationCriteriaDTO()));

    Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());
    var theaterPages = this.theaterService.findAll(specification, pageable);

    var paginationResponse =
        PaginationResponse.<TheaterResponse>builder()
            .data(
                theaterPages
                    .get()
                    .map(
                        theater ->
                            TheaterResponse.builder()
                                .id(theater.getId())
                                .name(theater.getTheaterName())
                                .description(theater.getDescription())
                                .firstImage(theater.getFirstImage())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalElements(theaterPages.getNumberOfElements())
            .totalPages(theaterPages.getTotalPages())
            .build();

    return BaseResponse.build(paginationResponse, true);
  }

  @Override
  public BaseResponse<PaginationResponse<RoomResponse>> findRoomByFilter(RoomCriteria criteria) {
    Specification<Room> specification = RoomSpecification.hasTheaterId(criteria.getTheaterId());

    Pageable pageable = PageRequest.of(criteria.getCurrentPage(), criteria.getPageSize());
    var roomPages = this.roomService.getAllRooms(specification, pageable);
    var roomResponse =
        PaginationResponse.<RoomResponse>builder()
            .data(
                roomPages
                    .get()
                    .map(
                        room ->
                            RoomResponse.builder()
                                .Id(room.getId())
                                .roomName(room.getRoomName())
                                .monitorHeight(room.getMonitorHeight())
                                .monitorWidth(room.getMonitorWidth())
                                .totalSeatNumber(room.getTotalSeatNumber())
                                .theaterId(room.getTheaterId())
                                .build())
                    .toList())
            .currentPage(criteria.getCurrentPage())
            .totalElements(roomPages.getNumberOfElements())
            .totalPages(roomPages.getTotalPages())
            .build();
    return BaseResponse.build(null, true);
  }

  public void addLocationIntoTheater(LocationDTO locationDTO, Theater theater) {
    var location =
        Location.builder()
            .longitude(locationDTO.getLongitude())
            .latitude(locationDTO.getLatitude())
            .build();
    theater.addLocation(location);
  }

  public void addTheaterAssets(List<AssetDTO> theaterAssetDTOS, Theater theater) {
    theaterAssetDTOS.stream()
        .map(
            theaterAssetDTO ->
                TheaterAsset.builder()
                    .mediaKey(theaterAssetDTO.getMediaKey())
                    .mediaType(theaterAssetDTO.getMediaType())
                    .build())
        .forEach(theater::addTheaterAsset);
  }

  public void addRoomsIntoTheater(List<RoomDTO> roomDTOS, Theater theater) {
    roomDTOS.stream()
        .map(
            roomDTO ->
                Room.builder()
                    .roomName(roomDTO.getName())
                    .totalSeatNumber(roomDTO.getTotalSeatNumber())
                    .monitorHeight(roomDTO.getMonitorHeight())
                    .monitorWidth(roomDTO.getMonitorWidth())
                    .build())
        .forEach(theater::addRoom);
  }

  public void addFingerFoodIntoTheater(List<FingerFoodDTO> fingerFoodDTOS, Theater theater) {
    fingerFoodDTOS.stream()
        .map(
            fingerFoodDTO -> {
              var food =
                  FingerFood.builder()
                      .foodName(fingerFoodDTO.getFoodName())
                      .price(fingerFoodDTO.getPrice())
                      .foodType(fingerFoodDTO.getFoodType())
                      .build();
              fingerFoodDTO.getFoodAssetDTOS().stream()
                  .map(
                      foodAssetDTO ->
                          FingerFoodAsset.builder()
                              .mediaKey(foodAssetDTO.getMediaKey())
                              .mediaType(foodAssetDTO.getMediaType())
                              .build())
                  .forEach(food::addAsset);
              return food;
            })
        .forEach(theater::addFingerFood);
  }
}
