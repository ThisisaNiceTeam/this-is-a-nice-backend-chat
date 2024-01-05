package com.thisisaniceteam.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationCodeAlreadyAccountResponse {
    private Integer code;
    private String message;
    private String accessToken;
    private String refreshToken;
    private String nickname;
}
