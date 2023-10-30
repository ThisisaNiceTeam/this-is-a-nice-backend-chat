package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.domain.chatroom.repository.ChatRoomRepository;
import com.thisisaniceteam.chat.domain.memberchatroom.service.MemberChatRoomService;
import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomServiceImpl implements ChatRoomService{
    private final ChatRoomRepository chatRoomRepository;
    private final MemberChatRoomService memberChatRoomService;

    @Override
    public void connectChatRoom(Member member) {
        // 가장 오래전에 만들어진 대기상태의 채팅방 가져오기
        Optional<ChatRoom> chatRoomStateIsReadyForConnect = chatRoomRepository.getChatRoomStateIsReadyForConnect();
        if (chatRoomStateIsReadyForConnect.isPresent()) {
            ChatRoom chatRoom = chatRoomStateIsReadyForConnect.get();
            // 채팅방과 회원 사이 연관관계 매핑하기
            memberChatRoomService.createMemberChatRoom(member, chatRoom);
        }
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
}
