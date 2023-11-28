package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.model.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> getMemberByMemberId(Long memberId);
}
