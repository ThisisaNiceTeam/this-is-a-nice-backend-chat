package com.thisisaniceteam.chat.domain.chatroom.repository;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.WebSocket;

import java.util.ArrayList;
import java.util.Optional;

public interface ChatRoomCustomRepository {

    // 연결을 위해 가장 오래 대기중인 채팅방 가져오기
    Optional<ChatRoom> getChatRoomStateIsReadyForConnect();

    // 회원이 갖는 모든 채팅방 리스트 가져오기
    Optional<ArrayList<ChatRoom>> getChatRoomByMember(Member member);

    Optional<ChatRoom> getWaitedChatRoom();

    ChatRoom getChatRoomWithWebSocketSessions(Long chatRoomId);

    boolean checkMemberInRoom(Long chatRoomId, Long memberId);
}
