package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank
    private String token;

    @NotNull
    private MemberSocialType socialType;

}
