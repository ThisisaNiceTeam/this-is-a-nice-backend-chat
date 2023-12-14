package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private String nickname;

    public static LoginResponse of(String accessToken, String refreshToken, String nickname) {
        return new LoginResponse(accessToken, refreshToken, nickname);
    }
}
