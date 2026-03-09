package com.example.fitmotion.chatroom.service;

import com.example.fitmotion.chatroom.dto.ChatRoomResponseDto;
import com.example.fitmotion.chatroom.entity.ChatRoom;

public interface ChatRoomService {

    ChatRoomResponseDto findById(Long id);

    ChatRoom getEntity(Long id);
}
