package com.thisisaniceteam.chat.common.provider;

import com.thisisaniceteam.chat.common.client.naver.NaverAuthApiClient;
import com.thisisaniceteam.chat.common.client.naver.dto.NaverToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
@RequiredArgsConstructor
public class NaverAuthProvider implements AuthProvider{
    private final NaverAuthApiClient naverAuthApiClient;

    @Override
    public String getSocialId(String token) {
        return naverAuthApiClient.getProfileInfo("Bearer " + token).getResponse().getId();
    }

    @Override
    public NaverToken getToken(String authorizationCode) {
        try {
            return naverAuthApiClient.getNaverToken(authorizationCode);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    public String getNaverName(String token) {
        return naverAuthApiClient.getProfileInfo(token).getName();
    }
}
