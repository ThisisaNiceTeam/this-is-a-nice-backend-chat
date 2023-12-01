package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAgreement memberAgreement;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberProfileImage memberProfileImage;

    public List<ChatRoom> getAllChatRooms() {
        List<ChatRoom> chatRooms = new ArrayList<>();
        for (MemberChatRoom memberChatRoom : memberChatRooms) {
            chatRooms.add(memberChatRoom.getChatRoom());
        }

        return chatRooms;
    }
}
