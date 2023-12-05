package com.thisisaniceteam.chat.model.entity;


import com.thisisaniceteam.chat.model.MemberProfile;
import com.thisisaniceteam.chat.model.MemberSocialInfo;
import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private MemberSocialInfo memberSocialInfo;

    @Embedded
    private MemberProfile profile;

    @Column(length = 30)
    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAgreement memberAgreement;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberProfileImage memberProfileImage;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(String socialId, MemberSocialType socialType, String nickname, String profileUrl, String profilePath) {
        this.memberSocialInfo = MemberSocialInfo.of(socialId, socialType);
        this.profile = MemberProfile.of(profileUrl, profilePath);
        this.nickname = nickname;
    }


    public static Member newMember(String socialId, MemberSocialType socialType, String nickname, String profileUrl, String profilePath) {
        return Member.builder()
                .socialId(socialId)
                .socialType(socialType)
                .nickname(nickname)
                .profileUrl(profileUrl)
                .profilePath(profilePath)
                .build();
    }
}
