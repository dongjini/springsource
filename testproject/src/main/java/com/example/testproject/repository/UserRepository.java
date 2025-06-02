package com.example.testproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
