package com.thisisaniceteam.chat.domain.chatroomwebsocket.service;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.WebSocket;

import java.util.List;

public interface ChatRoomWebSocketService {
    // 채팅방과 웹소켓의 연관관계 매핑
    void createChatRoomWebSocket(List<ChatRoom> chatRooms, WebSocket webSocket);
}
