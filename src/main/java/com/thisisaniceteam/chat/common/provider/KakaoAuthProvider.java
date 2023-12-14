package com.thisisaniceteam.chat.common.provider;

import com.thisisaniceteam.chat.common.client.kakao.KakaoAuthApiClient;
import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoProfileResponse;
import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@RequiredArgsConstructor
@Component
public class KakaoAuthProvider implements AuthProvider {
    private final KakaoAuthApiClient kakaoAuthApiClient;
    @Override
    public String getSocialId(String token) {
        return kakaoAuthApiClient.getProfileInfo("Bearer " + token).getId();
    }

    @Override
    public KakaoToken getKakaoToken(String authorizationCode){
        try {
            return kakaoAuthApiClient.getKakaoToken(authorizationCode);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }
}
