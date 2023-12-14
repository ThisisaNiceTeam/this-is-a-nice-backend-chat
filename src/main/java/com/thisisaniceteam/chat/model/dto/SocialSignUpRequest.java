package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialSignUpRequest {
    @NotBlank
    private String token;

    @NotNull
    private MemberSocialType socialType;

    private String nickname;

    private String region;
    private String classRoom;
    private String name;

    public CreateMemberRequest toCreateMemberRequest(String socialId) {
        return CreateMemberRequest.of(socialId, socialType, nickname, region, classRoom, name);
    }
}
