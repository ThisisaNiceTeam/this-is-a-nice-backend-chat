package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.dto.CreateMemberRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    Optional<Member> getMemberByMemberId(Long memberId);

    Long registerMember(CreateMemberRequest createMemberRequest, MultipartFile profileImage) throws IOException, Exception;

    Boolean validateNotExistsUser(String socialId, MemberSocialType socialType);
}
