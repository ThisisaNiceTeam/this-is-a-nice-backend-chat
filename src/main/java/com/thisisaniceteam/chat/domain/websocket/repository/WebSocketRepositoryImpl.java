package com.thisisaniceteam.chat.domain.websocket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class WebSocketRepositoryImpl implements WebSocketCustomRepository {

    private final JPAQueryFactory queryFactory;
}
