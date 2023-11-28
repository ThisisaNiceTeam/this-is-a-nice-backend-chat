package com.thisisaniceteam.chat.domain.memberchatroom.service;

import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.MemberChatRoom;

public interface MemberChatRoomService {
    MemberChatRoom createMemberChatRoom(Member member, ChatRoom chatRoom);
}
