package com.thisisaniceteam.chat.common.provider;

import com.thisisaniceteam.chat.common.client.dto.Token;
import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;
import com.thisisaniceteam.chat.common.client.naver.dto.NaverToken;

public interface AuthProvider {
    String getSocialId(String token);

    Token getToken(String authorizationCode);
}
