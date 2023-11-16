package com.thisisaniceteam.chat.domain.chatroom.controller;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.model.dto.CreateChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @PostMapping("/chatroom")
    public ResponseEntity<CreateChatRoomResponse> createChatRoom() {
        chatRoomService.createChatRoom(1L);
        return ResponseEntity.ok(CreateChatRoomResponse.of(1L));
    }
}
