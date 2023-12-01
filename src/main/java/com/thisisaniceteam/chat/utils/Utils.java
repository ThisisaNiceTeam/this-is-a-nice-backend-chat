package com.thisisaniceteam.chat.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thisisaniceteam.chat.model.dto.Chat;

public class Utils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utils() {
    }

    public static Chat getObject(final String chat) throws Exception {
        return objectMapper.readValue(chat, Chat.class);
    }

    public static String getString(final Chat chat) throws Exception {
        return objectMapper.writeValueAsString(chat);
    }
}
