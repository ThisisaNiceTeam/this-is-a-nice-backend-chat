package com.thisisaniceteam.chat.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomWebSocket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomWebSocketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "web_socket_id")
    private WebSocket webSocket;

    public static ChatRoomWebSocket createChatRoomWebSocket(ChatRoom chatRoom, WebSocket webSocket) {
        return ChatRoomWebSocket.builder()
                .chatRoom(chatRoom)
                .webSocket(webSocket)
                .build();
    }
}
