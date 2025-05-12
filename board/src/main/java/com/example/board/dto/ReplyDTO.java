package com.example.board.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReplyDTO {

    private Long rno;
    private String text;

    private String replyerEmail;
    private String replyerName;

    // 게시글 번호(부모번호)
    private Long bno;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
