package com.thisisaniceteam.chat.domain.message.service;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.Message;

import java.util.List;

public interface MessageService {
    void createMessage(Long memberId, ChatRoom chatRoom, String contents);

    List<Member> getMembersInChatRoom(String chatRoomId);
}
