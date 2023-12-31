package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRoomResponse {
    private Integer code;
    private String message;
    private Long chatroomId;
}
