package com.thisisaniceteam.chat.domain.message.repository;

import com.thisisaniceteam.chat.model.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
