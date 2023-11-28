package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;

import java.util.ArrayList;
import java.util.Optional;

public interface ChatRoomService {
    // 대기 중인 채팅방에 해당 회원 추가하기
    boolean MemberToChatRoom(ChatRoom chatRoom, Member member);

    // 보유한 채팅방 모두 가져오기
    ArrayList<ChatRoom> getChatRoomList(Member member);

    // 대기중인 채팅방 오래된 순으로 가져오기
    Optional<ChatRoom> getWaitedChatRoom();
}
