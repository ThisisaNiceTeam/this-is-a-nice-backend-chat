package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserAgreement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userAgreementId;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
