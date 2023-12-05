package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialSignUpRequest {
    @NotBlank
    private String token;

    @NotBlank
    @Length(min = 1, max = 7)
    private String nickname;

    @NotNull
    private MemberSocialType socialType;

    public CreateMemberRequest toCreateMemberRequest(String socialId) {
        return CreateMemberRequest.of(socialId, socialType, nickname);
    }
}
