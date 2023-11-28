package com.thisisaniceteam.chat.domain.chatroom.repository;

import com.thisisaniceteam.chat.model.ChatRoom;
import com.thisisaniceteam.chat.model.RoomState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>, ChatRoomCustomRepository{
    Optional<ChatRoom> getChatRoomByRoomStateOrderByCreatedAtDesc(RoomState roomState);
}