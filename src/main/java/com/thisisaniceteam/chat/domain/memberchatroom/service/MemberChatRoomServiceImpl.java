package com.thisisaniceteam.chat.domain.memberchatroom.service;

import com.thisisaniceteam.chat.domain.memberchatroom.repository.MemberChatRoomRepository;
import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.Member;
import com.thisisaniceteam.chat.model.MemberChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberChatRoomServiceImpl implements MemberChatRoomService{
    private final MemberChatRoomRepository memberChatRoomRepository;

    @Override
    public void createMemberChatRoom(Member member, ChatRoom chatRoom) {
        // 채팅방-회원 연관관계 엔티티 생성
        MemberChatRoom memberChatRoom = new MemberChatRoom();
        // 연관관계 매핑
        memberChatRoom.addMemberAndChatRoom(member, chatRoom);
        // 저장
        memberChatRoomRepository.save(memberChatRoom);
    }
}
