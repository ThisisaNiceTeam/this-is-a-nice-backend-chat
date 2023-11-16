package com.thisisaniceteam.chat.common.config;

import com.thisisaniceteam.chat.common.handler.WebSocketHandler;
import com.thisisaniceteam.chat.common.interceptor.HandShakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final HandShakeInterceptor handShakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry
                .addHandler(webSocketHandler, "/ws/chat")
                .addInterceptors(handShakeInterceptor)
                .setAllowedOrigins("*")
                .withSockJS();
    }
}
