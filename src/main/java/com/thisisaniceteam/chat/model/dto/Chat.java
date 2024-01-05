package com.thisisaniceteam.chat.model.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Chat {
    private String type;
    private String sender;
    private String chatRoomId;
    private String receiver;
    private Object message;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void newConnect() {
        this.type = "new";
    }

    public void closeConnect() {
        this.type = "close";
    }
}
