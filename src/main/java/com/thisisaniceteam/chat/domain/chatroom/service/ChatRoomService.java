package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.dto.CreateChatRoomResponse;

import java.util.ArrayList;

public interface ChatRoomService {
    // 채팅 방 생성
    CreateChatRoomResponse createChatRoom(Member member);

    // 대기 중인 채팅방에 해당 회원 추가하기
    void connectChatRoom(Member member);

    // 보유한 채팅방 모두 가져오기
    ArrayList<ChatRoom> getChatRoomList(Member member);
}
