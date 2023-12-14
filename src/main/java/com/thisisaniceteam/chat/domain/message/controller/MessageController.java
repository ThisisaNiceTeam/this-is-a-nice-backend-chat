package com.thisisaniceteam.chat.domain.message.controller;

import com.thisisaniceteam.chat.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
}
