package com.thisisaniceteam.chat.domain.auth.service;

import com.thisisaniceteam.chat.common.client.dto.Token;
import com.thisisaniceteam.chat.model.dto.LoginRequest;
import com.thisisaniceteam.chat.model.dto.LoginResponse;
import com.thisisaniceteam.chat.model.dto.SocialSignUpRequest;
import com.thisisaniceteam.chat.model.dto.SocialSignUpResponse;


public interface AuthService {
    Token getToken(String authorizationCode);
    SocialSignUpResponse signUp(SocialSignUpRequest request) throws Exception;

    LoginResponse login(LoginRequest request);

    void withdrawal(Long memberId);

    void createRefreshToken(Long memberId, String refreshToken);

    void deleteRefreshToken(String memberId);

    String getKakaoSocialId(String accessToken);

    String getNaverSocialId(String accessToken);

    String getNaverName(String accessToken);
}
