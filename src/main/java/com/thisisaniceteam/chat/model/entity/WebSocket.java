package com.thisisaniceteam.chat.model.entity;

import com.thisisaniceteam.chat.model.WebSocketState;
import com.thisisaniceteam.chat.model.entity.BaseEntity;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
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
public class WebSocket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long webSocketId;

    private String sessionId;

    @Enumerated(EnumType.STRING)
    private WebSocketState webSocketState;

    @OneToMany(mappedBy = "webSocket", cascade = CascadeType.ALL)
    private List<ChatRoomWebSocket> chatRoomWebSocket = new ArrayList<>();

    public static WebSocket createWebSocket(String sessionId) {
        return WebSocket.builder()
                .sessionId(sessionId)
                .webSocketState(WebSocketState.USE)
                .build();
    }

    public void deleteWebSocket() {
        this.webSocketState = WebSocketState.DELETED;
    }
}
