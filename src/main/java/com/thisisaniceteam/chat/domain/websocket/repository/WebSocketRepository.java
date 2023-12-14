package com.thisisaniceteam.chat.domain.websocket.repository;

import com.thisisaniceteam.chat.model.entity.WebSocket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WebSocketRepository extends JpaRepository<WebSocket, Long>, WebSocketCustomRepository {
    List<WebSocket> findAllBySessionId(String sessionId);
}