package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private Long memberId;

    public static LoginResponse of(String accessToken, String refreshToken, Long memberId) {
        return new LoginResponse(accessToken, refreshToken, memberId);
    }
}
