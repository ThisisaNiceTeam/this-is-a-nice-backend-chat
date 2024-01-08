package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chat {
    private String type;
    private String chatRoomId;
    private String message;
}
