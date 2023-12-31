package com.thisisaniceteam.chat.common.client.kakao.dto;

import lombok.Getter;

@Getter
public class KakaoAccount {
    private Boolean has_email;
    private Boolean email_needs_agreement;
    private Boolean is_email_valid;
    private Boolean is_email_verified;
    private String email;
}
