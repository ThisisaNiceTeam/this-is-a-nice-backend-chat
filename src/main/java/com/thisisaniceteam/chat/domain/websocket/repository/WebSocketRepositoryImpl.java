package com.thisisaniceteam.chat.domain.websocket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.thisisaniceteam.chat.model.entity.QChatRoom;
import com.thisisaniceteam.chat.model.entity.QChatRoomWebSocket;
import com.thisisaniceteam.chat.model.entity.QWebSocket;
import com.thisisaniceteam.chat.model.entity.WebSocket;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

import static com.thisisaniceteam.chat.model.entity.QChatRoom.chatRoom;
import static com.thisisaniceteam.chat.model.entity.QChatRoomWebSocket.chatRoomWebSocket;
import static com.thisisaniceteam.chat.model.entity.QWebSocket.webSocket;


@RequiredArgsConstructor
public class WebSocketRepositoryImpl implements WebSocketCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public ArrayList<WebSocket> getWebSockets(Long chatRoomId) {
        return (ArrayList<WebSocket>) queryFactory.selectFrom(webSocket)
                .leftJoin(webSocket.chatRoomWebSocket, chatRoomWebSocket)
                .leftJoin(chatRoomWebSocket.chatRoom, chatRoom)
                .where(chatRoom.chatRoomId.eq(chatRoomId))
                .fetch();
    }
}
