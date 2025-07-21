package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.TheaterDTO;
import com.james.scheduleservice.facade.TheaterFacade;
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
  @GetMapping(value = "/internal/{id}", headers = "secret-key=user-service001")
  @ResponseStatus(HttpStatus.OK)
  public TheaterDTO findScheduleById(@PathVariable Long id) {
    return this.theaterFacade.findTheaterById(id);
  }
}
