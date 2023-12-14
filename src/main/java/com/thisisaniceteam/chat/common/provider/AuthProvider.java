package com.thisisaniceteam.chat.common.provider;

import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;

public interface AuthProvider {
    String getSocialId(String token);

    KakaoToken getKakaoToken(String authorizationCode);
}
