package com.thisisaniceteam.chat.domain.member.repository;

import com.thisisaniceteam.chat.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member,Long>, MemberCustomRepository {

}