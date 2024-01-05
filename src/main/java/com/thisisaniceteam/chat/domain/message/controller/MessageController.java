package com.thisisaniceteam.chat.domain.message.controller;

import com.thisisaniceteam.chat.domain.message.service.MessageService;
import com.thisisaniceteam.chat.model.dto.Chat;
import com.thisisaniceteam.chat.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello")
    public void message(Chat chat) {
        // 해당 채팅 방에 참여중인 사람들에게 메시지를 전송한다.
        // 당연히 본인을 제외해야 한다.
        String chatRoomId = chat.getChatRoomId();
        List<Member> membersInChatRoom = messageService.getMembersInChatRoom(chatRoomId);
        for (Member member : membersInChatRoom) {
            simpMessageSendingOperations.convertAndSend("/sub/channel/" + member.getMemberId(), chat);
//            if (!member.getMemberId().equals(Long.valueOf(chat.getSender()))) {
//                simpMessageSendingOperations.convertAndSend("/sub/channel/" + member.getMemberId(), chat);
//            }
        }
    }
}
