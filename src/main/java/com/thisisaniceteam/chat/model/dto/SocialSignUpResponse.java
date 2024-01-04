package com.thisisaniceteam.chat.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialSignUpResponse {
    @Schema(description = "accessToken Value")
    private String accessToken;
    @Schema(description = "refreshToken Value")
    private String refreshToken;
    @Schema(description = "nickname")
    private String nickname;
    @Schema(description = "response message")
    private String message;

    public static SocialSignUpResponse of(String accessToken, String refreshToken, String nickname) {
        return new SocialSignUpResponse(accessToken, refreshToken, nickname, "회원가입을 완료했습니다.");
    }
}