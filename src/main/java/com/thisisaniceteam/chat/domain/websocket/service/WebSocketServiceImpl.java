package com.thisisaniceteam.chat.domain.websocket.service;

import com.thisisaniceteam.chat.domain.websocket.repository.WebSocketRepository;
import com.thisisaniceteam.chat.model.entity.WebSocket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WebSocketServiceImpl implements WebSocketService{
    private final WebSocketRepository webSocketRepository;


    @Override
    public List<WebSocket> getAllBySessionId(String sessionId) {
        return webSocketRepository.findAllBySessionId(sessionId);
    }

    @Override
    public void updateAll(List<WebSocket> webSockets) {
        webSocketRepository.saveAll(webSockets);
    }

    @Override
    public WebSocket createWebSocket(String sessionId) {
        WebSocket webSocket = WebSocket.createWebSocket(sessionId);
        return webSocketRepository.save(webSocket);
    }
}
