package com.thisisaniceteam.chat.domain.ChatRoomMessage.service;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Message;

public interface ChatRoomMessageService {
    void createChatRoomMessage(ChatRoom chatRoom, Message message);
}
