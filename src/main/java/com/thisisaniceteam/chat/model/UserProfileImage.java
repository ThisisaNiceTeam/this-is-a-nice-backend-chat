package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserProfileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userProfileImageId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
