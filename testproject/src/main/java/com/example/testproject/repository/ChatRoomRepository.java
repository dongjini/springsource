package com.example.testproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testproject.entity.ChatRoom;
import com.example.testproject.entity.User;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    Optional<ChatRoom> findByUser1AndUser2(User user1, User user2);
}
