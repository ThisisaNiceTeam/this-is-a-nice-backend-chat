package com.thisisaniceteam.chat.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SsafyInfo {
    private String region;
    private String classRoom;
    private String name;

    public static SsafyInfo of(String region, String classRoom, String name) {
        return new SsafyInfo(region, classRoom, name);
    }
}
