package com.thisisaniceteam.chat.domain.auth.service;

import com.thisisaniceteam.chat.common.provider.AuthProvider;
import com.thisisaniceteam.chat.domain.auth.provider.AuthProviderFinder;
import com.thisisaniceteam.chat.domain.member.repository.MemberRepository;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.domain.refreshtoken.repository.RefreshTokenRepository;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.dto.LoginRequest;
import com.thisisaniceteam.chat.model.dto.SocialSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final AuthProviderFinder authProviderFinder;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Long signUp(SocialSignUpRequest socialSignUpRequest, MultipartFile profileImage) throws IOException {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(socialSignUpRequest.getSocialType());
        String socialId = authProvider.getSocialId(socialSignUpRequest.getToken());
        return memberService.registerMember(socialSignUpRequest.toCreateMemberRequest(socialId), profileImage);
    }

    @Override
    @Transactional(readOnly = true)
    public Long login(LoginRequest request) {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(request.getSocialType());
        String socialId = authProvider.getSocialId(request.getToken());
        return memberRepository.findMemberBySocialInfo(socialId, request.getSocialType()).getMemberId();
    }

    @Override
    public void withdrawal(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isEmpty()) {
            throw new RuntimeException("존재하지 않는 유저입니다.");
        }
        memberRepository.delete(member.get());
    }

    @Override
    public void deleteRefreshToken(String memberId) {
        refreshTokenRepository.deleteByMemberId(Long.valueOf(memberId));
    }
}
