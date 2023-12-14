package com.thisisaniceteam.chat.domain.memberchatroom.service;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.MemberChatRoom;

public interface MemberChatRoomService {
    MemberChatRoom createMemberChatRoom(Member member, ChatRoom chatRoom);
}
