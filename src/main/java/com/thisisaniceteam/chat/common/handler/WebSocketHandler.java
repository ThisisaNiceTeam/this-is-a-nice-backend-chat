package com.thisisaniceteam.chat.common.handler;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.MemberChatRoom;
import com.thisisaniceteam.chat.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    private final ChatRoomService chatRoomService;
    private final MemberService memberService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        /**
         * 시나리오 1. 해당 웹 소켓의 연결은 상대방이 있습니다.
         */

        /**
         * 시나리오 2. 해당 웹 소켓의 연결은 아직 상대방이 없습니다.
         */

        String payload = textMessage.getPayload();

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection Start");
        // session store
        Map<String, Object> attributes = session.getAttributes();
        String userId = (String) attributes.get("userId");
        sessionMap.put(userId, session);

        Optional<ChatRoom> waitedChatRoom = chatRoomService.getWaitedChatRoom();
        Optional<Member> optionalMember = memberService.getMemberByMemberId(Long.valueOf(userId));
        /**
         * 시나리오 1. 연결되지 않은 채팅방이 있다.
         * 연결되지 않은 채팅방 중 가장 오래된 채팅방을 가져와서 연결해준다.
         */
        if (waitedChatRoom.isPresent() && optionalMember.isPresent()) {
            ChatRoom chatRoom = waitedChatRoom.get();
            Member member = optionalMember.get();
            chatRoomService.MemberToChatRoom(chatRoom, member);

//            MemberChatRoom memberChatRoom1 = chatRoom.getMemberChatRoom().get(0);
//            MemberChatRoom memberChatRoom2 = chatRoom.getMemberChatRoom().get(1);
//            Member member1 = memberChatRoom1.getMember();
//            Member member2 = memberChatRoom2.getMember();
//
//            if (member1.getMemberId().equals(Long.parseLong(userId))) {
//                attributes.put("receiver", member2.getMemberId());
//            } else {
//                attributes.put("receiver", member1.getMemberId());
//            }
        }
        /**
         * 시나리오 2. 연결되지 않은 채팅방이 없다.
         * 채팅방을 연결되지 않은 상태로 새로 생성해준다.
         */
        else {

        }

        log.info("Connection End");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
        log.info("Connection Closed");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        log.info("전송 오류 발생");
    }
}
