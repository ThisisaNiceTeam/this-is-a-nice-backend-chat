package com.thisisaniceteam.chat.common.handler;

import com.google.gson.Gson;
import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.domain.message.service.MessageService;
import com.thisisaniceteam.chat.model.*;
import com.thisisaniceteam.chat.model.dto.Chat;
import com.thisisaniceteam.chat.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {

    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    private final MessageService messageService;

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        /**
         * 한 채팅방에는 2명의 인원이 있지만 여러 기기를 통해 세션이 여러 개 일 수 있다. 따라서 모든 세션에 메시지를 전송해준다.
         * DB에 저장하는 건 한 번만 저장해준다.
         */
        Chat chat = Utils.getObject(textMessage.getPayload());
        chat.setSender(session.getId());
        Optional<ChatRoom> chatRoomById = chatRoomService.getChatRoomById(Long.parseLong(chat.getReceiver()));

        if (chatRoomById.isEmpty()) {
            throw new RuntimeException();
        }

        ChatRoom chatRoom = chatRoomService.getChatRoomById(Long.parseLong(chat.getReceiver())).get();
        Set<WebSocket> webSockets = chatRoom.getWebSockets();

        for (WebSocket webSocket : webSockets) {
            if (!webSocket.getWebSocketId().equals(Long.parseLong(chat.getSender()))) {
                sessionMap.get(String.valueOf(webSocket.getWebSocketId())).sendMessage(new TextMessage(Utils.getString(chat)));
            }
        }

        Message message = Message.createMessage(Long.parseLong((String) session.getAttributes().get("userId")), chatRoom.getChatRoomId());
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

//        Optional<ChatRoom> waitedChatRoom = chatRoomService.getWaitedChatRoom();
//        Optional<Member> optionalMember = memberService.getMemberByMemberId(Long.valueOf(2));
//        /**
//         * 시나리오 1. 연결되지 않은 채팅방이 있다.
//         * 연결되지 않은 채팅방 중 가장 오래된 채팅방을 가져와서 연결해준다.
//         */
//        if (waitedChatRoom.isPresent() && optionalMember.isPresent()) {
//            ChatRoom chatRoom = waitedChatRoom.get();
//            Member member = optionalMember.get();
//            MemberChatRoom memberChatRoom = chatRoomService.MemberToChatRoom(chatRoom, member);
//            chatRoom.roomIsCompleted();
//            chatRoomService.updateChatRoomState(chatRoom);
//        }
//        /**
//         * 시나리오 2. 연결되지 않은 채팅방이 없다.
//         * 채팅방을 연결되지 않은 상태로 새로 생성해준다.
//         */
//        else if (waitedChatRoom.isEmpty() && optionalMember.isPresent()) {
//            Member member = optionalMember.get();
//            ChatRoom newChatRoom = chatRoomService.createChatRoom();
//
//            chatRoomService.MemberToChatRoom(newChatRoom, member);
//        } else {
//            afterConnectionClosed(session, CloseStatus.NO_STATUS_CODE);
//        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("Connection Closed");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        log.info("전송 오류 발생");
    }
}
