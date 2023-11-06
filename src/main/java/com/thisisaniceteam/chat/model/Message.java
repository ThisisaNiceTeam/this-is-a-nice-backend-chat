package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
        name = "message", indexes = {
        @Index(name = "chat_room_id", columnList = "chatRoomId"),
        @Index(name = "member_id", columnList = "memberId")
})
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private String memberId;

    private String chatRoomId;

    private Boolean isRead;
}
