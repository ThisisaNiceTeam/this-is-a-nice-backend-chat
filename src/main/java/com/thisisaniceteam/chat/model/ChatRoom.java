package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
public class ChatRoom extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_chat_room_id")
    private MemberChatRoom memberChatRoom;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private ArrayList<ChatRoomMessage> chatRoomMessages = new ArrayList<>();
}
