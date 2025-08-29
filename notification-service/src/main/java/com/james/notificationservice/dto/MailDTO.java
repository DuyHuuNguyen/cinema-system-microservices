package com.james.notificationservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class MailDTO {
    private String to;
    private String subject;
    private String body;
}
