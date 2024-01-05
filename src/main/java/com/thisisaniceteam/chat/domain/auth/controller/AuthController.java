package com.thisisaniceteam.chat.domain.auth.controller;

import com.thisisaniceteam.chat.common.client.dto.Token;
import com.thisisaniceteam.chat.domain.auth.service.AuthService;
import com.thisisaniceteam.chat.domain.member.service.MemberService;
import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.dto.*;
import com.thisisaniceteam.chat.utils.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "인증 컨트롤러", description = "회원가입, 로그인, 로그아웃, 회원탈퇴, 인증 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final JWTUtil jwtUtil;
    private final MemberService memberService;

    @Operation(summary = "인가코드 전달")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "잘못된 인가코드", content = @Content(
                    schema = @Schema(implementation = AuthorizationCodeWrongResponse.class)
            )),
            @ApiResponse(responseCode = "200", description = "이미 계정이 있어서 로그인을 완료했습니다.", content = @Content(
                    schema = @Schema(implementation = AuthorizationCodeAlreadyAccountResponse.class)
            )),
            @ApiResponse(responseCode = "202", description = "계정이 없어서 계정을 생성할 때 추가정보가 필요합니다.", content = @Content(
                    schema = @Schema(implementation = AuthorizationCodeMoreInformationResponse.class)
            ))
    })
    @PostMapping("/authorization")
    public ResponseEntity<?> passAuthorizationCode(
            @Parameter(required = true, description = "인가코드입니다", content = @Content(
                    schema = @Schema(implementation = String.class, description = "인가코드")
            )) String authorizationCode
    ) {
        log.info(authorizationCode);
        Token token = authService.getToken(authorizationCode);
        // 3 인가 코드로 발급이 불가능한 경우
        if (token == null) {
            AuthorizationCodeWrongResponse authorizationCodeWrongResponse = new AuthorizationCodeWrongResponse(400, "인가 코드가 잘못되었습니다.");
            return new ResponseEntity<>(authorizationCodeWrongResponse, HttpStatus.valueOf(400));
        } else {
            // 인가 코드로 토큰을 발급 받았음
            String naverSocialId = authService.getNaverSocialId(token.getAccess_token());

            // 이미 계정이 있는 유저
            if (memberService.validateNotExistsUser(naverSocialId, MemberSocialType.NAVER)) {
                // 로그인을 진행한다.
                LoginRequest loginRequest = new LoginRequest(token.getAccess_token(), MemberSocialType.NAVER);
                LoginResponse loginResponse = authService.login(loginRequest);
                AuthorizationCodeAlreadyAccountResponse authorizationCodeAlreadyAccountResponse = new AuthorizationCodeAlreadyAccountResponse(200, "로그인을 완료했습니다.",
                        loginResponse.getAccessToken(), loginResponse.getRefreshToken(), loginResponse.getNickname());
                return new ResponseEntity<>(authorizationCodeAlreadyAccountResponse, HttpStatus.valueOf(200));
            } else {
                // 계정이 없는 유저
                AuthorizationCodeMoreInformationResponse authorizationCodeMoreInformationResponse = new AuthorizationCodeMoreInformationResponse(202, "추가 정보가 필요합니다.",
                        token.getAccess_token(), authService.getNaverName(token.getAccess_token()));
                return new ResponseEntity<>(authorizationCodeMoreInformationResponse, HttpStatus.valueOf(202));
            }
        }
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입에 성공했습니다.", content = @Content(
                    schema = @Schema(implementation = SocialSignUpResponse.class)
            ))
    })
    @Operation(summary = "소셜 회원가입")
    // 이상 Swagger 코드
    @PostMapping("/social-signup")
    public ResponseEntity<?> signUp(
            @Valid @RequestBody @Parameter(required = true, description = "회원 가입에 필요한 추가 정보입니다. 인가 코드 전달을 통해 받은 토큰을 함께 전달합니다.")
            SocialSignUpRequest socialSignUpRequest
    ) throws Exception {
        SocialSignUpResponse socialSignUpResponse = authService.signUp(socialSignUpRequest);
        return ResponseEntity.ok(socialSignUpResponse);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃에 성공했습니다.", content = @Content(
                    schema = @Schema(implementation = String.class)
            ))
    })
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

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴에 성공했습니다.", content = @Content(
                    schema = @Schema(implementation = String.class)
            ))
    })
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
