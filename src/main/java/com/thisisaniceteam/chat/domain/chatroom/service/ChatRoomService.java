package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.MemberChatRoom;
import com.thisisaniceteam.chat.model.dto.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    // 대기 중인 채팅방에 해당 회원 추가하기
    MemberChatRoom memberToChatRoom(ChatRoom chatRoom, Member member);

    // 보유한 채팅방 모두 가져오기
    ArrayList<ChatRoom> getChatRoomList(Member member);

    // 대기중인 채팅방 오래된 순으로 가져오기
    Optional<ChatRoom> getWaitedChatRoom();

    // 채팅방 새롭게 생성하기
    ChatRoom createChatRoom(Long memberId);

    // 채팅방 상태 전환 업데이트하기
    void updateChatRoomState(ChatRoom chatRoom);

    // 해당 채팅방 가져오기
    Optional<ChatRoom> getChatRoomById(Long chatRoomId);

    boolean checkMemberInRoom(ChatRoom chatRoom, Member member);

    ArrayList<String> getWebSocketSessionIdInChatRoom(ChatRoom chatRoom);


//    // 연결되어 있는 webSocketSession List 반환
//    List<Long> getWebSocketSessionIdInUse(Long chatRoomId, Chat chat);

    // 회원이 참가중인 채팅방에 회원의 웹소켓 추가하기
}
