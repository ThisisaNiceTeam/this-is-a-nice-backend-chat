package com.thisisaniceteam.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private String type;
    private String sender;
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
