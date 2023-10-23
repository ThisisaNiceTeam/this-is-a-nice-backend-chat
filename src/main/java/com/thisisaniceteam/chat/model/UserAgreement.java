package com.thisisaniceteam.chat.model;

import com.thisisaniceteam.chat.model.Member;
import jakarta.persistence.*;

@Entity
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userAgreementId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
