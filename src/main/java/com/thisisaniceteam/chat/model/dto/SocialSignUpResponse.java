package com.thisisaniceteam.chat.model.dto;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialSignUpResponse {
    private String accessToken;
    private String refreshToken;
    private String nickname;
    private String message;

    public static SocialSignUpResponse of(String accessToken, String refreshToken, String nickname) {
        return new SocialSignUpResponse(accessToken, refreshToken, nickname, "회원가입을 완료했습니다.");
    }
}