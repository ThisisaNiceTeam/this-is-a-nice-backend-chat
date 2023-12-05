package com.thisisaniceteam.chat.domain.auth.service;

import com.thisisaniceteam.chat.model.dto.LoginRequest;
import com.thisisaniceteam.chat.model.dto.SocialSignUpRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AuthService {
    Long signUp(SocialSignUpRequest request, MultipartFile profileImage) throws IOException;

    Long login(LoginRequest request);

    void withdrawal(Long memberId);

    void deleteRefreshToken(String memberId);
}
