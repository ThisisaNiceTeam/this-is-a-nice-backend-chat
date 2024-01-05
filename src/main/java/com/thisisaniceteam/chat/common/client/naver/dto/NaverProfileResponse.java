package com.thisisaniceteam.chat.common.client.naver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverProfileResponse {
    private String resultcode;
    private String message;
    private NaverProfileDetailResponse response;
    private String name;
}
