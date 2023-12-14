package com.thisisaniceteam.chat.domain.member.service;

import com.thisisaniceteam.chat.domain.member.repository.MemberRepository;
import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.dto.CreateMemberRequest;
import com.thisisaniceteam.chat.model.dto.FileUploadResponse;
import com.thisisaniceteam.chat.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final FileUploadUtil fileUploadUtil;

    @Override
    public Optional<Member> getMemberByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member registerMember(CreateMemberRequest createMemberRequest, MultipartFile profileImage) throws Exception {
        MemberServiceHelper.validateNotExistsUser(memberRepository, createMemberRequest.getSocialId(), createMemberRequest.getSocialType());
        // TODO 나중에 데이터베이스에서 싸피 사람들을 조회하고 맞는 사람들만 가입할 수 있도록 검증추가
        if (profileImage != null) {
            FileUploadResponse fileUploadResponse = fileUploadUtil.uploadFile("image", profileImage);
            Member member = memberRepository.save(createMemberRequest.toEntity(fileUploadResponse));
            return member;
        } else {
            Member member = memberRepository.save(createMemberRequest.toEntity(new FileUploadResponse()));
            return member;
        }
    }

    @Override
    public Boolean validateNotExistsUser(String socialId, MemberSocialType socialType) {
        return memberRepository.existMemberBySocialInfo(socialId, socialType);
    }
}
