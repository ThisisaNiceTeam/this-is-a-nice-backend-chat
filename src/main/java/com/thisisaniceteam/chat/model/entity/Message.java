package com.thisisaniceteam.chat.model.entity;

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
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<ChatRoomMessage> chatRoomMessageList = new ArrayList<>();

    private Long memberId;

    private String contents;

    public static Message createMessage(Long memberId, String contents) {
        return Message.builder()
                .memberId(memberId)
                .contents(contents)
                .build();
    }
}