package com.thisisaniceteam.chat.domain.message.service;

import com.thisisaniceteam.chat.domain.message.repository.MessageRepository;
import com.thisisaniceteam.chat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Override
    public void createMessage(Message message) {
        messageRepository.save(message);
    }
}
