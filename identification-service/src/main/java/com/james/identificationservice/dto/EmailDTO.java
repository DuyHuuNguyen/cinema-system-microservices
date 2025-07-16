package com.james.identificationservice.dto;

import lombok.*;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmailDTO {
  private String toEmail;
  private String subject;
  private String body;
}
