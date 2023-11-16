package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @Enumerated(EnumType.STRING)
    private RoomState roomState;

    @OneToMany(mappedBy = "chatRoom",cascade = CascadeType.ALL)
    private ArrayList<MemberChatRoom> memberChatRoom;

    public static ChatRoom createChatRoom() {
        return new ChatRoomBuilder()
                .roomState(RoomState.WAIT)
                .build();
    }
}
