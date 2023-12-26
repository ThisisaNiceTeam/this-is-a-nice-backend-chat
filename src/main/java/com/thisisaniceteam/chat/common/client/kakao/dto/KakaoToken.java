package com.thisisaniceteam.chat.common.client.kakao.dto;

import com.thisisaniceteam.chat.common.client.dto.Token;


public class KakaoToken extends Token {
    private String id_token;
    private String scope;
    private Integer refresh_token_expires_in;
}
