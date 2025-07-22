package com.james.scheduleservice.facade.Impl;

import com.james.scheduleservice.config.SecurityUserDetails;
import com.james.scheduleservice.dto.*;
import com.james.scheduleservice.entity.*;
import com.james.scheduleservice.enums.ErrorCode;
import com.james.scheduleservice.exception.EntityNotFoundException;
import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.resquest.UpsertTheaterRequest;
import com.james.scheduleservice.resquest.ValidAdminTheaterRequest;
import com.james.scheduleservice.service.TheaterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TheaterFacadeImpl implements TheaterFacade {
  private final TheaterService theaterService;

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

    this.theaterService.save(theater);
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
