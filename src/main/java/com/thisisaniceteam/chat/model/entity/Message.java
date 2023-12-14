package com.thisisaniceteam.chat.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    private Long memberId;

    private Long chatRoomId;

    public static Message createMessage(Long memberId, Long chatRoomId) {
        return Message.builder()
                .memberId(memberId)
                .chatRoomId(chatRoomId)
                .build();
    }
}