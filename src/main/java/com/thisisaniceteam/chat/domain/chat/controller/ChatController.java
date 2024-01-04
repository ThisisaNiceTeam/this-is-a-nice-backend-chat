package com.thisisaniceteam.chat.domain.chat.controller;

import com.thisisaniceteam.chat.domain.chatroom.service.ChatRoomService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;

@Tag(name = "채팅 컨트롤러", description = "채팅 관련 API 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "새로운 채팅 시작하기", description = "개인 당 유지 가능한 채팅방은 최대 5개입니다." +
            "해당 API는 대기 중인 채팅방에 대하여 참여를 뜻 합니다." +
            "대기 중인 채팅방이 없다면 채팅방을 생성하고 참여를 기다립니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "대기 중인 채팅방에 추가했습니다.", content = @Content(
                    schema = @Schema(implementation = HashMap.class)
            )),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 토큰입니다.", content = @Content(
                    schema = @Schema(implementation = HashMap.class)
            )),
            @ApiResponse(responseCode = "202", description = "참여 가능한 채팅방이 없습니다.", content = @Content(
                    schema = @Schema(implementation = HashMap.class)
            ))
    })
    @PostMapping("/new")
    public ResponseEntity<?> startNewChat(@RequestHeader String authorization) {
        if (jwtUtil.checkToken(authorization)) {
            log.info("유효한 토큰입니다.");
            String memberId = jwtUtil.getMemberId(authorization);
            log.info("회원 ID : ".concat(memberId));
            Optional<ChatRoom> waitedChatRoom = chatRoomService.getWaitedChatRoom();
            if (waitedChatRoom.isPresent()) {
                log.info("참여 가능한 채팅방이 있습니다.");
                ChatRoom chatRoom = waitedChatRoom.get();
                Optional<Member> memberByMemberId = memberService.getMemberByMemberId(Long.valueOf(memberId));
                chatRoomService.memberToChatRoom(chatRoom, memberByMemberId.get());
                log.info("해당 채팅방에 회원을 넣었습니다.");
            } else {
                log.info("참여 가능한 채팅방이 없습니다.");

            }
        }
        /**
         * 1. 참여 가능한 채팅방 조회 -> 새롭게 생성 X
         * 2. 참여 가능한 채팅방이 있다면 해당 채팅방에 회원을 넣어준다. -> 채팅방에 변화 -> 여기서 트랜잭션 끝
         * 3. 참여 가능한 채팅방이 없다면 현재 참여 가능한 채팅방이 없다고 알린다. -> 변화 X
         */
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
