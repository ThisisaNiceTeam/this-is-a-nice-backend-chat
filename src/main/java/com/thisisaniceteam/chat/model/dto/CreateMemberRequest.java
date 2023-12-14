package com.thisisaniceteam.chat.model.dto;

import com.thisisaniceteam.chat.model.MemberProfile;
import com.thisisaniceteam.chat.model.MemberSocialInfo;
import com.thisisaniceteam.chat.model.SsafyInfo;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.MemberSocialType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class CreateMemberRequest {
    private String socialId;

    @NotNull
    private MemberSocialType socialType;

    @Length(min = 1, max = 7)
    private String nickname;
    private String region;
    private String classRoom;
    private String name;

    public static CreateMemberRequest of(String socialId, MemberSocialType socialType, String nickname, String region, String classRoom, String name) {
        return new CreateMemberRequest(socialId, socialType, nickname, region, classRoom, name);
    }

    public Member toEntity(FileUploadResponse fileUploadResponse) {
        return Member.newMember(MemberSocialInfo.of(socialId, socialType), MemberProfile.of(fileUploadResponse.getFileUrl(), fileUploadResponse.getFileUrl()), SsafyInfo.of(region, classRoom, name));
    }
}
