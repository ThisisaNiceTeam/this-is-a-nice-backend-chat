package com.thisisaniceteam.chat.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SsafyInfo {
    private String region;
    private String group;

    private SsafyInfo(String region, String group) {
        this.region = region;
        this.group = group;
    }
}
