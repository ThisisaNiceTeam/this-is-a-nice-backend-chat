package com.thisisaniceteam.chat.domain.memberchatroom.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberChatRoomRepositoryImpl implements MemberChatRoomCustomRepository {
    private final JPAQueryFactory queryFactory;
}
