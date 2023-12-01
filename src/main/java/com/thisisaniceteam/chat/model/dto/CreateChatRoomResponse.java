package com.thisisaniceteam.chat.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateChatRoomResponse {
    private Long chatRoomId;

    public static CreateChatRoomResponse of(Long chatRoomId) {
        return new CreateChatRoomResponse(chatRoomId);
    }
}
