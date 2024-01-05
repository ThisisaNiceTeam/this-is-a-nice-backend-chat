package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.dto.CreateMemberRequest;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {
    Optional<Member> getMemberByMemberId(Long memberId);

    Member registerMember(CreateMemberRequest createMemberRequest) throws Exception;

    Boolean validateNotExistsUser(String socialId, MemberSocialType socialType);
}
