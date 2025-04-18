package com.example.jpa.repository;

import java.time.LocalDateTime;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;
import com.example.jpa.entity.Memo;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    // CRUD
    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 10).forEach(i -> {
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("writer" + i)
                    .regdate(LocalDateTime.now())
                    .build();
            boardRepository.save(board);
        });
    }

    @Test
    public void updateTest() {

        Board board = boardRepository.findById(2L).get();
        board.setTitle("memoText update");
        boardRepository.save(board);
    }

    @Test
    public void readTest() {
        Board board = boardRepository.findById(3L).get();
        System.out.println(board);
    }

    @Test
    public void listTest() {
        boardRepository.findAll().forEach(memo -> {
            System.out.println(memo);
        });
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(10L);
    }
}
