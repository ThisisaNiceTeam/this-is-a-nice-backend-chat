package com.thisisaniceteam.chat.domain.chatroomwebsocket.repository;

import com.thisisaniceteam.chat.model.entity.ChatRoomWebSocket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomWebSocketRepository extends JpaRepository<ChatRoomWebSocket, Long>, ChatRoomWebSocketCustomRepository {

}