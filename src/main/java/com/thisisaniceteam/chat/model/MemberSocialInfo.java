package com.thisisaniceteam.chat.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSocialInfo {
    private String socialId;


    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private MemberSocialType socialType;

    private MemberSocialInfo(String socialId, MemberSocialType socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public static MemberSocialInfo of(String socialId,MemberSocialType socialType) {
        return new MemberSocialInfo(socialId, socialType);
    }
}
