package com.thisisaniceteam.chat.domain.chatroom.controller;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.model.dto.CreateChatRoomResponse;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatroom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "새로운 채팅방 만들기", description = "채팅방을 새롭게 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "채팅방을 새로 만들었습니다.", content = @Content(
                    schema = @Schema(implementation = CreateChatRoomResponse.class)
            )),
            @ApiResponse(responseCode = "400", description = "잘못된 토큰입니다.", content = @Content(
                    schema = @Schema(implementation = HashMap.class)
            ))
    })
    @PostMapping("/new")
    public ResponseEntity<?> createChatRoom(@RequestHeader String authorization) {
        if (jwtUtil.checkToken(authorization)) {
            log.info("유효한 토큰입니다.");
            String memberId = jwtUtil.getMemberId(authorization);
            log.info("회원 ID : ".concat(memberId));
            ChatRoom chatRoom = chatRoomService.createChatRoom(Long.valueOf(memberId));

            CreateChatRoomResponse createChatRoomResponse = new CreateChatRoomResponse(200, "채팅방을 새로 만들었습니다.", chatRoom.getChatRoomId());
            return new ResponseEntity<>(createChatRoomResponse, HttpStatus.valueOf(200));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
