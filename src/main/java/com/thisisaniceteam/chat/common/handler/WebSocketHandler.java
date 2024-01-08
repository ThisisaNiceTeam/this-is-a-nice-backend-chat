package com.thisisaniceteam.chat.common.handler;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.domain.chatroomwebsocket.service.ChatRoomWebSocketService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.domain.message.service.MessageService;
import com.thisisaniceteam.chat.domain.websocket.service.WebSocketService;
import com.thisisaniceteam.chat.model.dto.Chat;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.Message;
import com.thisisaniceteam.chat.model.entity.WebSocket;
import com.thisisaniceteam.chat.utils.ChatUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
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

    private final ChatRoomWebSocketService chatRoomWebSocketService;
    private final MemberService memberService;

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        log.info("Handle Message Start");
        /**
         * 한 채팅방에는 2명의 인원이 있지만 여러 기기를 통해 세션이 여러 개 일 수 있다. 따라서 모든 세션에 메시지를 전송해준다.
         * DB에 저장하는 건 한 번만 저장해준다.
         */

        /**
         * 1. 채팅을 보낸 사용자가 보낸 메시지가 도착할 채팅방에 사용자가 포함되어 있는 지 확인
         * 2. 포함되어 있다면 해당 채팅방에 포함된 웹소켓에 메시지를 전송해준다. (본인제외)
         * 3. 해당 채팅방에 메시지를 저장해준다.
         */
        Chat chat = ChatUtil.getObject(textMessage.getPayload());
        String memberId = (String) session.getAttributes().get("memberId");
        ChatRoom chatRoom = chatRoomService.getChatRoomById(Long.valueOf(chat.getChatRoomId())).get();
        Member member = memberService.getMemberByMemberId(Long.valueOf(memberId)).get();

        if (chatRoomService.checkMemberInRoom(chatRoom, member)) {
            ArrayList<String> sessionIds = chatRoomService.getWebSocketSessionIdInChatRoom(chatRoom);
            for (String sessionId : sessionIds) {
                if(sessionId.equals(session.getId())) continue;

                sessionMap.get(sessionId).sendMessage(new TextMessage(ChatUtil.getString(chat)));
            }
        }

        messageService.createMessage(Long.valueOf(memberId), chatRoom, chat.getMessage());
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        /**
         * 회원의 웹소켓이 연결되면 해당 회원 ID를 가져온다.
         * 회원이 연결되어 있는 모든 채빙방에 해당 웹소켓을 추가해준다.
         * 이후 메시지를 보낼 때, 해당 메시지에 채팅방 번호가 가진 웹소켓이 일치하면 메시지를 전송해주는 방식으로 구현한다.
         */
        log.info("Connection Start");
        // Servlet Request 에서 저장해줬던 데이터
        Map<String, Object> attributes = session.getAttributes();
        String memberId = (String) attributes.get("memberId");
        log.info("웹 소켓에 연결된 회원 아이디는 : ".concat(memberId));

        String sessionId = session.getId();

        // 1. 웹소켓 생성
        WebSocket webSocket = webSocketService.createWebSocket(sessionId);
        ArrayList<ChatRoom> chatRoomList = chatRoomService.getChatRoomList(memberService.getMemberByMemberId(Long.valueOf(memberId)).get());
        // 2. 웹소켓과 채팅방들의 연관관계 매핑
        chatRoomWebSocketService.createChatRoomWebSocket(chatRoomList, webSocket);

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
