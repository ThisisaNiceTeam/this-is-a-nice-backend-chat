package com.thisisaniceteam.chat.domain.auth.provider;


import com.thisisaniceteam.chat.common.provider.AuthProvider;
import com.thisisaniceteam.chat.model.MemberSocialType;


public interface AuthProviderFinder {
    AuthProvider findAuthProvider(MemberSocialType socialType);
}