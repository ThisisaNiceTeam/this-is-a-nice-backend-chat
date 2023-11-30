package com.thisisaniceteam.chat.domain.chatroom.service;

import com.thisisaniceteam.chat.domain.chatroom.repository.ChatRoomRepository;
import com.thisisaniceteam.chat.domain.memberchatroom.service.MemberChatRoomService;
import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.MemberChatRoom;
import com.thisisaniceteam.chat.model.RoomState;
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
}
