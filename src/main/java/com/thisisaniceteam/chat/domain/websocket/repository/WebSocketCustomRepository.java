package com.thisisaniceteam.chat.domain.websocket.repository;

import com.thisisaniceteam.chat.model.entity.WebSocket;

import java.util.ArrayList;

public interface WebSocketCustomRepository {
    ArrayList<WebSocket> getWebSockets(Long chatRoomId);
}
