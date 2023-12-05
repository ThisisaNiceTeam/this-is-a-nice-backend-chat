package com.thisisaniceteam.chat.common.provider;

import com.thisisaniceteam.chat.common.client.kakao.KakaoAuthApiClient;
import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoAuthProvider implements AuthProvider {
    private final KakaoAuthApiClient kakaoAuthApiClient;
    @Override
    public String getSocialId(String token) {
        KakaoProfileResponse response = kakaoAuthApiClient.getProfileInfo("Bearer " + token);
        return response.getId();
    }
}
