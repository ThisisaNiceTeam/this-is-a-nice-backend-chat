package com.thisisaniceteam.chat.domain.auth.controller;

import com.thisisaniceteam.chat.common.client.kakao.dto.KakaoToken;
import com.thisisaniceteam.chat.domain.auth.service.AuthService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.dto.LoginRequest;
import com.thisisaniceteam.chat.model.dto.LoginResponse;
import com.thisisaniceteam.chat.model.dto.SocialSignUpRequest;
import com.thisisaniceteam.chat.model.dto.SocialSignUpResponse;
import com.thisisaniceteam.chat.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JWTUtil jwtUtil;
    private final MemberService memberService;

    @Operation(summary = "인가코드 전달")
    @PostMapping("/authorization")
    public ResponseEntity<?> passAuthorizationCode(
            @Parameter(required = true, description = "인가코드입니다") String authorizationCode
    ) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = null;
        KakaoToken kakaoToken = authService.getKakaoToken(authorizationCode);
        // 3 인가 코드로 발급이 불가능한 경우
        if (kakaoToken == null) {
            response.put("message", "인가 코드가 잘못되었습니다.");
            status = HttpStatus.BAD_REQUEST;
        } else {
            // 인가 코드로 토큰을 발급 받았음
            String kakaoSocialId = authService.getKakaoSocialId(kakaoToken.getAccess_token());

            // 이미 계정이 있는 유저
            if (memberService.validateNotExistsUser(kakaoSocialId, MemberSocialType.KAKAO)) {
                // 로그인을 진행한다.
                LoginRequest loginRequest = new LoginRequest(kakaoToken.getAccess_token(), MemberSocialType.KAKAO);
                LoginResponse loginResponse = authService.login(loginRequest);
                response.put("access-token", loginResponse.getAccessToken());
                response.put("refresh-token", loginResponse.getRefreshToken());
                response.put("nickname", loginResponse.getNickname());
                response.put("message", "로그인을 완료했습니다.");
                status = HttpStatus.OK;
            } else {
                // 계정이 없는 유저
                response.put("kakao-access-token", kakaoToken.getAccess_token());
                response.put("message", "추가 정보가 필요합니다.");
                status = HttpStatus.OK;
            }
        }
        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "소셜 회원가입")
    // 이상 Swagger 코드
    @PostMapping("/social-signup")
    public ResponseEntity<?> signUp(
            @Valid @RequestPart @Parameter(required = true, description = "회원 가입에 필요한 추가 정보입니다. 인가 코드 전달을 통해 받은 토큰을 함께 전달합니다.")
            SocialSignUpRequest socialSignUpRequest,
            @RequestPart(required = false) @Parameter(required = false, description = "프로필 사진입니다.") MultipartFile profileImage
    ) throws Exception {
        SocialSignUpResponse socialSignUpResponse = authService.signUp(socialSignUpRequest, profileImage);
        return ResponseEntity.ok(socialSignUpResponse);
    }

    @Operation(summary = "로그아웃 요청")
    // 이상 Swagger 코드
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(value = "Authorization") String authorization
    ) {
        String memberId = jwtUtil.getMemberId(authorization);
        authService.deleteRefreshToken(memberId);
        return ResponseEntity.ok("로그아웃이 되었습니다.");
    }

    @Operation(summary = "회원 탈퇴 요청")
    // 이상 Swagger 코드
    @DeleteMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(
            @RequestHeader(value = "Authorization") String authorization
    ) {
        String memberId = jwtUtil.getMemberId(authorization);
        authService.withdrawal(Long.valueOf(memberId));
        return ResponseEntity.ok("회원 탈퇴가 되었습니다.");
    }
}
