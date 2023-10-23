package com.thisisaniceteam.chat.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Message {
    @Id
    private ObjectId id;
    private String content;
    private String sender;
    private String timestamp;
}
