package com.thisisaniceteam.chat.domain.memberchatroom.repository;

import com.thisisaniceteam.chat.model.entity.MemberChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChatRoomRepository extends JpaRepository<MemberChatRoom, Long> {
}
