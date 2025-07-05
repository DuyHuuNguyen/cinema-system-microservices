package com.james.bookingservice.dto;

import com.james.bookingservice.enums.RoleEnum;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValidTokenDTO {
    private UserDTO userDTO;
    private List<RoleEnum> roles;
}
