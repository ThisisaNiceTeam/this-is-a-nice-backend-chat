package com.thisisaniceteam.chat.domain.ChatRoomMessage.service;

import com.thisisaniceteam.chat.domain.ChatRoomMessage.repository.ChatRoomMessageRepository;
import com.thisisaniceteam.chat.domain.chatroom.repository.ChatRoomRepository;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.ChatRoomMessage;
import com.thisisaniceteam.chat.model.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomMessageServiceImpl implements ChatRoomMessageService{
    private final ChatRoomMessageRepository chatRoomMessageRepository;
    @Override
    public void createChatRoomMessage(ChatRoom chatRoom, Message message) {
        chatRoomMessageRepository.save(ChatRoomMessage.createChatRoomMessage(chatRoom, message));
    }
}
