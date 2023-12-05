package com.thisisaniceteam.chat.domain.auth.controller;

import com.thisisaniceteam.chat.domain.auth.service.AuthService;
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

import java.io.IOException;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "소셜 회원가입")
    // 이상 Swagger 코드
    @PostMapping("/auth/social-signup")
    public ResponseEntity<?> signUp(
            @Valid @RequestPart @Parameter(required = true) SocialSignUpRequest socialSignUpRequest,
            @RequestPart(required = false) @Parameter(required = false) MultipartFile profileImage
    ) throws IOException {

        Long memberId = authService.signUp(socialSignUpRequest, profileImage);
        HttpStatus status = HttpStatus.OK;
        String accessToken = jwtUtil.createAccessToken(String.valueOf(memberId));

        // refresh 저장 로직(데이터베이스) 추가
        String refreshToken = jwtUtil.createRefreshToken(String.valueOf(memberId));
        SocialSignUpResponse response = SocialSignUpResponse.of(accessToken, refreshToken, memberId);

        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "로그인 요청")
    // 이상 Swagger 코드
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(
            @Valid @RequestBody @Parameter(required = true) LoginRequest request
    ) {
        Long memberId = authService.login(request);
        HttpStatus status = HttpStatus.OK;
        String accessToken = jwtUtil.createAccessToken(String.valueOf(memberId));
        String refreshToken = jwtUtil.createRefreshToken(String.valueOf(memberId));

        LoginResponse response = LoginResponse.of(accessToken, refreshToken, memberId);

        return new ResponseEntity<>(response, status);
    }

    @Operation(summary = "로그아웃 요청")
    // 이상 Swagger 코드
    @PostMapping("/auth/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(value = "Authorization") String authorization
    ) {
        String memberId = jwtUtil.getMemberId(authorization);
        HttpStatus status = HttpStatus.OK;
        authService.deleteRefreshToken(memberId);

        return new ResponseEntity<>("로그아웃이 되었습니다.", status);
    }

    @Operation(summary = "회원 탈퇴 요청")
    // 이상 Swagger 코드
    @DeleteMapping("/auth/withdrawal")
    public ResponseEntity<String> withdrawal(
            @RequestHeader(value = "Authorization") String authorization
    ) {
        String memberId = jwtUtil.getMemberId(authorization);
        authService.withdrawal(Long.valueOf(memberId));
        return ResponseEntity.ok("회원 탈퇴가 되었습니다.");
    }
}
