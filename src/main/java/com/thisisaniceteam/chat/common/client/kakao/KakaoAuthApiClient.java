package com.thisisaniceteam.chat.common.client.kakao;

import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoAuthApiClient {

    @Transactional
    public KakaoProfileResponse getProfileInfo(@RequestHeader("Authorization") String accessToken) {
        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", accessToken)
                .retrieve()
                .body(KakaoProfileResponse.class);
    }
}
