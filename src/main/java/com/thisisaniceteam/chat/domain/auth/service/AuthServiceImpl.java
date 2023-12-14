package com.thisisaniceteam.chat.domain.auth.service;

import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;
import com.thisisaniceteam.chat.common.provider.AuthProvider;
import com.thisisaniceteam.chat.domain.auth.provider.AuthProviderFinder;
import com.thisisaniceteam.chat.domain.member.repository.MemberRepository;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.domain.refreshtoken.repository.RefreshTokenRepository;
import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.dto.LoginResponse;
import com.thisisaniceteam.chat.model.dto.SocialSignUpResponse;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.dto.LoginRequest;
import com.thisisaniceteam.chat.model.dto.SocialSignUpRequest;
import com.thisisaniceteam.chat.model.entity.RefreshToken;
import com.thisisaniceteam.chat.utils.JWTUtil;
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
    private final JWTUtil jwtUtil;

    @Override
    public KakaoToken getKakaoToken(String authorizationCode) {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(MemberSocialType.KAKAO);
        return authProvider.getKakaoToken(authorizationCode);
    }

    @Override
    public SocialSignUpResponse signUp(SocialSignUpRequest socialSignUpRequest, MultipartFile profileImage) throws Exception {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(socialSignUpRequest.getSocialType());
        String socialId = authProvider.getSocialId(socialSignUpRequest.getToken());
        Member member = memberService.registerMember(socialSignUpRequest.toCreateMemberRequest(socialId), profileImage);
        String accessToken = jwtUtil.createAccessToken(String.valueOf(member.getMemberId()));
        String refreshToken = jwtUtil.createRefreshToken(String.valueOf(member.getMemberId()));
        createRefreshToken(member.getMemberId(), refreshToken);
        return SocialSignUpResponse.of(accessToken, refreshToken, member.getNickname());
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(request.getSocialType());
        String socialId = authProvider.getSocialId(request.getToken());
        Member member = memberRepository.findMemberBySocialInfo(socialId, request.getSocialType());
        String accessToken = jwtUtil.createAccessToken(String.valueOf(member.getMemberId()));
        String refreshToken = jwtUtil.createRefreshToken(String.valueOf(member.getMemberId()));
        createRefreshToken(member.getMemberId(), refreshToken);
        return LoginResponse.of(accessToken, refreshToken, member.getNickname());
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
    public void createRefreshToken(Long memberId, String refreshToken) {
        refreshTokenRepository.save(RefreshToken.createRefreshToken(memberId, refreshToken));
    }

    @Override
    public void deleteRefreshToken(String memberId) {
        refreshTokenRepository.deleteByMemberId(Long.valueOf(memberId));
    }

    @Override
    public String getKakaoSocialId(String accessToken) {
        AuthProvider authProvider = authProviderFinder.findAuthProvider(MemberSocialType.KAKAO);
        return authProvider.getSocialId(accessToken);
    }
}
