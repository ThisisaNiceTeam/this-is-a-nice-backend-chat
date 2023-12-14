package com.thisisaniceteam.chat.model.entity;


import com.thisisaniceteam.chat.model.MemberProfile;
import com.thisisaniceteam.chat.model.MemberSocialInfo;
import com.thisisaniceteam.chat.model.MemberSocialType;
import com.thisisaniceteam.chat.model.SsafyInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Embedded
    private MemberSocialInfo memberSocialInfo;

    @Embedded
    private MemberProfile profile;

    @Embedded
    private SsafyInfo ssafyInfo;

    @Column(length = 30)
    private String nickname;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberChatRoom> memberChatRooms = new ArrayList<>();

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberAgreement memberAgreement;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberProfileImage memberProfileImage;

    public static Member newMember(MemberSocialInfo memberSocialInfo,
                                    MemberProfile memberProfile,
                                    SsafyInfo ssafyInfo) {
        return Member.builder()
                .memberSocialInfo(memberSocialInfo)
                .profile(memberProfile)
                .ssafyInfo(ssafyInfo)
                .build();
    }
}
