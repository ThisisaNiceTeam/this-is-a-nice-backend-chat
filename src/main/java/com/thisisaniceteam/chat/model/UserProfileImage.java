package com.thisisaniceteam.chat.model;

import com.thisisaniceteam.chat.model.Member;
import jakarta.persistence.*;

@Entity
public class UserProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfileImageId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
