package com.thisisaniceteam.chat.common.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private String expires_in;
}
