package com.thisisaniceteam.chat.model;

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
public class WebSocket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long webSocketId;

    private String sessionId;

    @Enumerated(EnumType.STRING)
    private WebSocketState webSocketState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    public void deleteWebSocket() {
        this.webSocketState = WebSocketState.DELETED;
    }
}
