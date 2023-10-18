package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Embedded
    private SsafyInfo ssafyInfo;

    @Embedded
    private MemberSocialInfo memberSocialInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private UserAgreement userAgreement;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private UserProfileImage userProfileImage;
}
