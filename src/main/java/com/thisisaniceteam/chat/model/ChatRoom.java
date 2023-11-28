package com.thisisaniceteam.chat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<MemberChatRoom> memberChatRoom = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<WebSocket> webSockets = new ArrayList<>();

    public static ChatRoom createChatRoom() {
        return new ChatRoomBuilder()
                .roomState(RoomState.WAIT)
                .build();
    }

    public void roomIsReady() {
        this.roomState = RoomState.COMPLETED;
    }

    public void roomIsRemoved() {
        this.roomState = RoomState.REMOVED;
    }

    public void roomIsCompleted() {
        this.roomState = RoomState.COMPLETED;
    }
}
