package com.thisisaniceteam.chat.model.entity;

import com.thisisaniceteam.chat.model.RoomState;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;

    @Enumerated(EnumType.STRING)
    private RoomState roomState;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<MemberChatRoom> memberChatRoom = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private Set<ChatRoomWebSocket> chatRoomWebSockets = new HashSet<>();

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<ChatRoomMessage> chatRoomMessageList = new ArrayList<>();

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
