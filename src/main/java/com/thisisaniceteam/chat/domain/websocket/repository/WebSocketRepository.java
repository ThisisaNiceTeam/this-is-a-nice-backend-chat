package com.thisisaniceteam.chat.domain.websocket.repository;

import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.WebSocket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface WebSocketRepository extends JpaRepository<WebSocket,Long>, WebSocketCustomRepository {
    List<WebSocket> findAllBySessionId(String sessionId);

    void deleteAll(List<WebSocket> webSockets);
}