package com.thisisaniceteam.chat.domain.chatroomwebsocket.service;

import com.thisisaniceteam.chat.domain.chatroomwebsocket.repository.ChatRoomWebSocketRepository;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.ChatRoomWebSocket;
import com.thisisaniceteam.chat.model.entity.WebSocket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomWebSocketServiceImpl implements ChatRoomWebSocketService{
    private final ChatRoomWebSocketRepository chatRoomWebSocketRepository;

    @Override
    public void createChatRoomWebSocket(List<ChatRoom> chatRooms, WebSocket webSocket) {
        for (ChatRoom chatRoom : chatRooms) {
            ChatRoomWebSocket chatRoomWebSocket = ChatRoomWebSocket.createChatRoomWebSocket(chatRoom, webSocket);
            chatRoomWebSocketRepository.save(chatRoomWebSocket);
        }
    }
}
