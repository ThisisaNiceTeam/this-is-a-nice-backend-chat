package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.domain.member.repository.MemberRepository;
import com.thisisaniceteam.chat.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> getMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }
}