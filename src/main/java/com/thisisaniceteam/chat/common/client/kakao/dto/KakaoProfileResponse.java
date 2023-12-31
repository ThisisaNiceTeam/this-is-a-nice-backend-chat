package com.thisisaniceteam.chat.common.client.kakao.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoProfileResponse {
    private String id;
    private String connected_at;
    private KakaoAccount kakao_account;
}
