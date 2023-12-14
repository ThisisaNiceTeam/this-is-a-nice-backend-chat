package com.thisisaniceteam.chat.domain.refreshtoken.repository;

import com.thisisaniceteam.chat.model.entity.MemberChatRoom;
import com.thisisaniceteam.chat.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByMemberId(Long memberId);
}
