package com.thisisaniceteam.chat.domain.memberchatroom.service;

import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;

public interface MemberChatRoomService {
    void createMemberChatRoom(Member member, ChatRoom chatRoom);
}
