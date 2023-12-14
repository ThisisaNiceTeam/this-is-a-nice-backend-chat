package com.thisisaniceteam.chat.common.client.kakao;

import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoProfileResponse;
import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoAuthApiClient {
    private final KakaoSecret kakaoSecret;

    public KakaoProfileResponse getProfileInfo(@RequestHeader("Authorization") String accessToken) {
        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", accessToken)
                .retrieve()
                .body(KakaoProfileResponse.class);
    }

    public KakaoToken getKakaoToken(String authorizationCode) throws HttpClientErrorException {
        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri("https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id="
                        + kakaoSecret.getClient_id()
                        + "&redirect_uri="
                        + kakaoSecret.getRedirect_uri()
                        + "&code="
                        + authorizationCode)
                .retrieve()
                .body(KakaoToken.class);
    }
}
