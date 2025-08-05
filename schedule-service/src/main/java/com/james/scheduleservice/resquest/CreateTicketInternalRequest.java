package com.james.scheduleservice.resquest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketInternalRequest {
    private Float price;
    private Integer totalSeats;
    private Long scheduleId;
}
