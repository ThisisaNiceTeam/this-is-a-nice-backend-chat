package com.thisisaniceteam.chat.domain.message.service;

import com.thisisaniceteam.chat.domain.chatroom.repository.ChatRoomRepository;
import com.thisisaniceteam.chat.domain.message.repository.MessageRepository;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.entity.MemberChatRoom;
import com.thisisaniceteam.chat.model.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void createMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public List<Member> getMembersInChatRoom(String chatRoomId) {
        List<Member> result = new ArrayList<>();
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(Long.valueOf(chatRoomId));
        if (chatRoomOptional.isPresent()) {
            ChatRoom chatRoom = chatRoomOptional.get();
            List<MemberChatRoom> memberChatRoomList = chatRoom.getMemberChatRoom();
            for (MemberChatRoom memberChatRoom : memberChatRoomList) {
                result.add(memberChatRoom.getMember());
            }
        }
        return result;
    }
}
