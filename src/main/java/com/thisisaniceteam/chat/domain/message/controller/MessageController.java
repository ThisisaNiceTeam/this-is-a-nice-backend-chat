package com.thisisaniceteam.chat.domain.message.controller;

import com.thisisaniceteam.chat.domain.message.service.MessageService;
import com.thisisaniceteam.chat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessageSendingOperations messageSendingOperations;
    private final MessageService messageService;

    @MessageMapping("chat")
    @SendToUser("/queue/message")
    public Message chat(Message message, SimpMessageHeaderAccessor accessor){
        Objects.requireNonNull(accessor.getSessionAttributes()).put("user", message.getMemberId());
        messageSendingOperations.convertAndSend("/topic/" + message.getChatRoomId(), message);
        messageService.createMessage(message);
        return message;
    }
}
