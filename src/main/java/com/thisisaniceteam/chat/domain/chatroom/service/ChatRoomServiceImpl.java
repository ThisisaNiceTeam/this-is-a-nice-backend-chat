package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.domain.chatroom.repository.ChatRoomRepository;
import com.thisisaniceteam.chat.domain.memberchatroom.service.MemberChatRoomService;
import com.thisisaniceteam.chat.model.*;
import com.thisisaniceteam.chat.model.dto.Chat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService{
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomService memberChatRoomService;

    @Override
    public MemberChatRoom MemberToChatRoom(ChatRoom chatRoom, Member member) {
        return memberChatRoomService.createMemberChatRoom(member, chatRoom);
    }

    @Override
    public ArrayList<ChatRoom> getChatRoomList(Member member) {
        ArrayList<ChatRoom> result = null;
        // 회원이 가진 채팅방 모두 가져오기
        Optional<ArrayList<ChatRoom>> chatRoomByMember = chatRoomRepository.getChatRoomByMember(member);
        if (chatRoomByMember.isPresent()) {
            result = chatRoomByMember.get();
        }
        // 채팅방 반환하기
        return result;
    }

    @Override
    public Optional<ChatRoom> getWaitedChatRoom() {
        return chatRoomRepository.getChatRoomByRoomStateOrderByCreatedAtDesc(RoomState.WAIT);
    }

    @Override
    public ChatRoom createChatRoom() {
        return chatRoomRepository.save(ChatRoom.createChatRoom());
    }

    @Override
    public void updateChatRoomState(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
    }

    @Override
    public Optional<ChatRoom> getChatRoomById(Long chatRoomId) {
        return chatRoomRepository.findById(chatRoomId);
    }

    @Override
    public List<Long> getWebSocketSessionIdInUse(Long chatRoomId, Chat chat) {
        ArrayList<Long> sessionIdList = new ArrayList<>();
        ChatRoom chatRoom = chatRoomRepository.getChatRoomWithWebSocketSessions(chatRoomId);
        Set<WebSocket> webSockets = chatRoom.getWebSockets();

        if (!webSockets.isEmpty()) {
            for (WebSocket webSocket : webSockets) {
                if (!webSocket.getWebSocketId().equals(Long.parseLong(chat.getSender())) & webSocket.getWebSocketState().equals(WebSocketState.USE)) {
                    sessionIdList.add(webSocket.getWebSocketId());
                }
            }
        }

        return sessionIdList;
    }
}
