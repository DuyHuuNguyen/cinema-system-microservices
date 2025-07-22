package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.facade.TheaterFacade;
import com.james.scheduleservice.resquest.ValidAdminTheaterRequest;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/theaters")
@RestController
@RequiredArgsConstructor
public class TheaterController {
  private final TheaterFacade theaterFacade;

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
}
