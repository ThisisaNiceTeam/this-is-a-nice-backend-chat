package com.thisisaniceteam.chat.domain.member.model;

import com.thisisaniceteam.chat.domain.chatroom.model.ChatRoom;
import com.thisisaniceteam.chat.domain.useragreement.model.UserAgreement;
import com.thisisaniceteam.chat.domain.userprofileimage.model.UserProfileImage;
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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ChatRoom> chatRooms = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private UserAgreement userAgreement;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private UserProfileImage userProfileImage;
}
