package com.thisisaniceteam.chat.domain.websocket.service;

import com.thisisaniceteam.chat.model.entity.WebSocket;

import java.util.List;

public interface WebSocketService {
    List<WebSocket> getAllBySessionId(String sessionId);

    void updateAll(List<WebSocket> webSockets);
}
