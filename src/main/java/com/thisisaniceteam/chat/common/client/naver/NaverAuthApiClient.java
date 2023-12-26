package com.thisisaniceteam.chat.common.client.naver;

import com.thisisaniceteam.chat.common.client.naver.dto.NaverProfileResponse;
import com.thisisaniceteam.chat.common.client.naver.dto.NaverToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class NaverAuthApiClient {
    private final NaverSecret naverSecret;

    public NaverProfileResponse getProfileInfo(String accessToken) {
        RestClient restClient = RestClient.create();

        NaverProfileResponse naverProfileResponse = restClient.get()
                .uri("https://openapi.naver.com/v1/nid/me")
                .header("Authorization", accessToken)
                .retrieve()
                .body(NaverProfileResponse.class);

        log.info(naverProfileResponse.toString());

        return naverProfileResponse;
    }

    public NaverToken getNaverToken(String authorizationCode) throws HttpClientErrorException {
        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri("https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&" +
                        "client_id=" +
                        naverSecret.getClient_id() +
                        "&client_secret=" +
                        naverSecret.getClient_secret() +
                        "&code=" +
                        authorizationCode +
                        "&state=" +
                        naverSecret.getState())
                .retrieve()
                .body(NaverToken.class);
    }
}
