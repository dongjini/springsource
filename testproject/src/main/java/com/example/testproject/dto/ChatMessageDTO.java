package com.example.testproject.dto;

import com.example.testproject.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ChatMessageDTO {

    private Long id;
    private User sender;
    private String content;

}
