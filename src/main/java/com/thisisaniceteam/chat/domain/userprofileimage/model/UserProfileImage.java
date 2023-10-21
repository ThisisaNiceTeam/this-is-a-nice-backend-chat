package com.thisisaniceteam.chat.domain.userprofileimage.model;

import com.thisisaniceteam.chat.domain.member.model.Member;
import jakarta.persistence.*;

@Entity
public class UserProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfileImageId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
