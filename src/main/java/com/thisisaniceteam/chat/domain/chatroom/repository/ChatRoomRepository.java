package com.thisisaniceteam.chat.domain.chatroom.repository;

import com.thisisaniceteam.chat.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomRepository extends JpaRepository<ChatRoom,Long>, ChatRoomCustomRepository{

}