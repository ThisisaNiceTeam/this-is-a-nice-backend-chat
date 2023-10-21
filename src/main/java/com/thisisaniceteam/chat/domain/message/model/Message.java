package com.thisisaniceteam.chat.domain.message.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@NoArgsConstructor
@Getter
public class Message {
    private String id;
    private String content;
    private String sender;
    private String timestamp;
}
