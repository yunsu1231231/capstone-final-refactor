package com.example.fitmotion.chatroom.service;

import com.example.fitmotion.chatroom.converter.ChatRoomConverter;
import com.example.fitmotion.chatroom.dto.ChatRoomResponseDto;
import com.example.fitmotion.chatroom.entity.ChatRoom;
import com.example.fitmotion.chatroom.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomConverter converter;

    @Override
    public ChatRoomResponseDto findById(Long id) {
        ChatRoom room = chatRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다. id=" + id));
        return converter.toResponseDto(room);
    }

    @Override
    public ChatRoom getEntity(Long id) {
        return chatRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("채팅방을 찾을 수 없습니다. id=" + id));
    }
}
