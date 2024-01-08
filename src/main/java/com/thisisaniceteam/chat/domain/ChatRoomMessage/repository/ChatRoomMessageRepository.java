package com.thisisaniceteam.chat.domain.ChatRoomMessage.repository;

import com.thisisaniceteam.chat.model.entity.ChatRoom;
import com.thisisaniceteam.chat.model.entity.ChatRoomMessage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ChatRoomMessageRepository extends JpaRepository<ChatRoomMessage,Long>, ChatRoomMessageCustomRepository {

}