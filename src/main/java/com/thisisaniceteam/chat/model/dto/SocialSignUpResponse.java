package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialSignUpResponse {

    private String accessToken;
    private String refreshToken;
    private String nickname;

    public static SocialSignUpResponse of(String accessToken, String refreshToken, String nickname) {
        return new SocialSignUpResponse(accessToken, refreshToken, nickname);
    }

}