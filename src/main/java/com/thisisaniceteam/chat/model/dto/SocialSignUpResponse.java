package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialSignUpResponse {

    private String accessToken;
    private String refreshToken;
    private Long memberId;

    public static SocialSignUpResponse of(String accessToken, String refreshToken, Long memberId) {
        return new SocialSignUpResponse(accessToken, refreshToken, memberId);
    }

}