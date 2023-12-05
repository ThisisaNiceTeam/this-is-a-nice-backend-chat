package com.thisisaniceteam.chat.domain.member.repository;

import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.MemberSocialType;

public interface MemberCustomRepository {
    Member findMemberBySocialInfo(String socialId, MemberSocialType socialType);
}
