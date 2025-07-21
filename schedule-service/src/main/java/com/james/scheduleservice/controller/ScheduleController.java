package com.james.scheduleservice.controller;

import com.james.scheduleservice.dto.ScheduleDTO;
import com.james.scheduleservice.facade.MovieScheduleFacade;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/schedules")
@RestController
@RequiredArgsConstructor
public class ScheduleController {
  private final MovieScheduleFacade scheduleFacade;

  @Hidden
  @GetMapping(value = "/internal/{id}", headers = "secret-key=user-service001")
  @ResponseStatus(HttpStatus.OK)
  public ScheduleDTO findScheduleById(@PathVariable Long id) {
    return this.scheduleFacade.findScheduleById(id);
  }
}
