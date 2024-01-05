package com.thisisaniceteam.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationCodeWrongResponse {
    private Integer code;
    private String message;
}
