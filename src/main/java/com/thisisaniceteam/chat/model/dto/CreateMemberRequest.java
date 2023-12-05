package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateMemberRequest {
    private String socialId;

    @NotNull
    private MemberSocialType socialType;

    @Length(min = 1, max = 7)
    private String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private CreateMemberRequest(String socialId, MemberSocialType socialType, String nickname) {
        this.socialId = socialId;
        this.socialType = socialType;
        this.nickname = nickname;
    }

    public static CreateMemberRequest of(String socialId, MemberSocialType socialType, String nickname) {
        return CreateMemberRequest.builder()
                .socialId(socialId)
                .socialType(socialType)
                .nickname(nickname)
                .build();
    }

    public Member toEntity(FileUploadResponse fileUploadResponse) {
        return Member.newMember(socialId, socialType, nickname, fileUploadResponse.getFileUrl(), fileUploadResponse.getFilePath());
    }
}
