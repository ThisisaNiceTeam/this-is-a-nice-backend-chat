package com.thisisaniceteam.chat.common.handler;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.domain.message.service.MessageService;
import com.thisisaniceteam.chat.domain.websocket.service.WebSocketService;
import com.thisisaniceteam.chat.model.*;
import com.thisisaniceteam.chat.model.dto.Chat;
import com.thisisaniceteam.chat.utils.ChatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatRoomService chatRoomService;

    private final MessageService messageService;
    private final WebSocketService webSocketService;

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        log.info("Handle Message Start");
        /**
         * 한 채팅방에는 2명의 인원이 있지만 여러 기기를 통해 세션이 여러 개 일 수 있다. 따라서 모든 세션에 메시지를 전송해준다.
         * DB에 저장하는 건 한 번만 저장해준다.
         */
        Chat chat = ChatUtil.getObject(textMessage.getPayload());
        chat.setSender(session.getId());

        List<Long> webSocketSessionIdInUse = chatRoomService.getWebSocketSessionIdInUse(Long.parseLong(chat.getReceiver()), chat);

        for (Long sessionId : webSocketSessionIdInUse) {
            sessionMap.get(String.valueOf(sessionId)).sendMessage(new TextMessage(ChatUtil.getString(chat)));
        }

        Message message = Message.createMessage(1L, Long.parseLong(chat.getReceiver()));
        messageService.createMessage(message);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        log.info("Connection Start");
        // Servlet Request 에서 저장해줬던 데이터
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");

        String sessionId = session.getId();
        sessionMap.put(sessionId, session);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        /**
         * 해당 유저가 가지고 있는 채팅방에서 소켓 내역들을 제거해준다.
         */
        log.info("Connection Closing Start");

        log.info("ChatRoom Session Removing Start");
        // 회원이 가진 모든 채팅방에서 해당 웹소켓 세션을 제거한다.
        List<WebSocket> allBySessionId = webSocketService.getAllBySessionId(session.getId());
        allBySessionId.forEach(WebSocket::deleteWebSocket);
        sessionMap.remove(session.getId());
        webSocketService.updateAll(allBySessionId);

        log.info("ChatRoom Session Removed");

        super.afterConnectionClosed(session, status);
        log.info("Connection Closed");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        log.info("전송 오류 발생");
    }
}
