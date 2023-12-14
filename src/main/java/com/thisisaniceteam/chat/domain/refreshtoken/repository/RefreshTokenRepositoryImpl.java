package com.thisisaniceteam.chat.domain.refreshtoken.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenCustomRepository {
    private final JPAQueryFactory queryFactory;
}
