package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank
    private String token;

    @NotNull
    private MemberSocialType socialType;
}
