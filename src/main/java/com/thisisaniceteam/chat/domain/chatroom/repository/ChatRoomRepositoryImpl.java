package com.thisisaniceteam.chat.domain.chatroom.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thisisaniceteam.chat.model.entity.*;
import com.thisisaniceteam.chat.model.RoomState;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thisisaniceteam.chat.model.entity.QChatRoom.*;
import static com.thisisaniceteam.chat.model.entity.QChatRoom.chatRoom;
import static com.thisisaniceteam.chat.model.entity.QChatRoomWebSocket.chatRoomWebSocket;
import static com.thisisaniceteam.chat.model.entity.QMember.member;
import static com.thisisaniceteam.chat.model.entity.QMemberChatRoom.memberChatRoom;
import static com.thisisaniceteam.chat.model.entity.QWebSocket.webSocket;


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

    @Override
    public boolean checkMemberInRoom(Long chatRoomId, Long memberId) {
        ChatRoom result = queryFactory.selectFrom(chatRoom)
                .leftJoin(chatRoom.memberChatRoom, memberChatRoom)
                .leftJoin(memberChatRoom.member, member)
                .where(member.memberId.eq(memberId))
                .fetchOne();

        return result != null;
    }
}
