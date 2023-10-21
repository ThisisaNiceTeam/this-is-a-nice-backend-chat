package com.thisisaniceteam.chat.domain.message.repository;

import com.thisisaniceteam.chat.domain.message.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
