package com.thisisaniceteam.chat.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thisisaniceteam.chat.model.entity.Member;
import com.thisisaniceteam.chat.model.MemberSocialType;
import lombok.RequiredArgsConstructor;

import static com.thisisaniceteam.chat.model.entity.QMember.member;


@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findMemberBySocialInfo(String socialId, MemberSocialType socialType) {
        return queryFactory
                .selectFrom(member)
                .where(
                        member.memberSocialInfo.socialId.eq(socialId),
                        member.memberSocialInfo.socialType.eq(socialType)
                ).fetchFirst();
    }

    @Override
    public boolean existMemberBySocialInfo(String socialId, MemberSocialType socialType) {
        return queryFactory
                .selectOne()
                .from(member)
                .where(
                        member.memberSocialInfo.socialId.eq(socialId),
                        member.memberSocialInfo.socialType.eq(socialType)
                ).fetchFirst() != null;
    }
}
