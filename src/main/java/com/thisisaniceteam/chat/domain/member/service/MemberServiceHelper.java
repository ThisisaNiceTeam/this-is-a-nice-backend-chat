package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.domain.member.repository.MemberRepository;
import com.thisisaniceteam.chat.model.MemberSocialType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceHelper {
    public static void validateNotExistsUser(MemberRepository memberRepository, String socialId, MemberSocialType socialType) {
        if (memberRepository.existMemberBySocialInfo(socialId, socialType)) {
            throw new IllegalArgumentException(String.format("이미 가입한 회원(%s - %s) 입니다", socialId, socialType));
        }
    }

//    public static Member getExistMember(MemberRepository memberRepository, Long memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(()->new NotFoundException("해당 id를 가진 회원이 존재하지 않습니다.", E404_NOT_EXISTS_MEMBER));
//    }
//
//    public static void validateMemberExists(MemberRepository memberRepository, Long memberId) {
//        if (!memberRepository.existsById(memberId)){
//            throw new NotFoundException("해당 id를 가진 회원이 존재하지 않습니다.", E404_NOT_EXISTS_MEMBER);
//        }
//    }
}
