package com.thisisaniceteam.chat.domain.useragreement.model;

import com.thisisaniceteam.chat.domain.member.model.Member;
import jakarta.persistence.*;

@Entity
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAgreementId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
