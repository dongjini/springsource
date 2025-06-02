package com.example.testproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testproject.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomIdOrderByTimestampAsc(Long roomId);

}
