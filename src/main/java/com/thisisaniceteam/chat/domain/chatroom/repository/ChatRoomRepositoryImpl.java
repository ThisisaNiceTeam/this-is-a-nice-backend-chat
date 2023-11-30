package com.thisisaniceteam.chat.domain.chatroom.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.RoomState;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;

import static com.thisisaniceteam.chat.model.QChatRoom.*;
import static com.thisisaniceteam.chat.model.QMember.member;
import static com.thisisaniceteam.chat.model.QMemberChatRoom.*;

@RequiredArgsConstructor
public class ChatRoomRepositoryImpl implements ChatRoomCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ChatRoom> getChatRoomStateIsReadyForConnect() {
        ChatRoom result = queryFactory.selectFrom(chatRoom)
                .leftJoin(chatRoom.memberChatRoom, memberChatRoom)
                .groupBy(chatRoom)
                .having(memberChatRoom.count().eq(Long.valueOf(1)))
                .orderBy(chatRoom.createdAt.asc())
                .fetchFirst();

        return Optional.of(result);
    }

    @Override
    public Optional<ArrayList<ChatRoom>> getChatRoomByMember(Member mem) {
        ArrayList<ChatRoom> result = (ArrayList<ChatRoom>) queryFactory.selectFrom(chatRoom)
                .leftJoin(chatRoom.memberChatRoom, memberChatRoom)
                .leftJoin(memberChatRoom.member, member)
                .where(member.eq(mem))
                .fetch();

        return Optional.of(result);
    }

    @Override
    public Optional<ChatRoom> getWaitedChatRoom() {
        ChatRoom result = queryFactory.selectFrom(chatRoom)
                .where(chatRoom.roomState.eq(RoomState.WAIT))
                .orderBy(chatRoom.createdAt.desc())
                .fetchFirst();

        return Optional.ofNullable(result);
    }

    @Override
    public ChatRoom getChatRoomWithWebSocketSessions(Long chatRoomId) {
        return queryFactory.selectFrom(chatRoom)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetchFirst();
    }
}
