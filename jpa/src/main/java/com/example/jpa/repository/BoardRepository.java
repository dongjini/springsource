package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.example.jpa.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, QuerydslPredicateExecutor<Board> {

    // WHERE b.WRITER ='writer4'
    // List<Board> findByWriter(String writer);

    // // title 이 board Title1 게시물 조회
    // List<Board> findByTitle(String title);

    // // b.WRITER like 'writer%'
    // List<Board> findByWriterStartingWith(String writer);

    // // b.WRITER like '%writer'
    // List<Board> findByWriterEndingWith(String writer);

    // // b.WRITER like '%writer%'
    // List<Board> findByWriterContaining(String writer);

    // // WHERE b.TITLE LIKE '%writer%' or CONTENT LIKE '%내용%'
    // List<Board> findByWriterContainingOrContentContaining(String writer, String
    // content);

    // // WHERE b.TITLE LIKE '%writer%' and CONTENT LIKE '%내용%'
    // List<Board> findByWriterContainingAndContentContaining(String writer, String
    // content);

    // // bno > 5 게시물 조회
    // List<Board> findByBnoGreaterThan(Long bno);

    // // bno > 0 order by bno desc
    // List<Board> findByBnoGreaterThanOrderByBnoDesc(Long bno);

    // // bno >=5 and bno <=10
    // // where bno between 5 and 10
    // List<Board> findByBnoBetween(Long start, Long end);

    // ----------------------------------------------------------
    // @Query
    // ----------------------------------------------------------

    // @Query("select b from Board b where b.writer = ?1")
    @Query("select b from Board b where b.writer = :writer")
    List<Board> findByWriter(@Param(":writer") String writer);

    @Query("select b from Board b where b.writer like ?1")
    List<Board> findByWriterStartingWith(String writer);

    @Query("select b from Board b where b.writer like %?1%")
    List<Board> findByWriterContaining(String writer);

    // 갖고 나오는게 2개 이상이면 Objec[] 배열이다.
    @Query("select b.title, b.writer from Board b where b.title like %?1%")
    List<Object[]> findByTitle2(String title);

    // SQL 구문 형식 사용 nativeQuery = true
    // @Query(value = "select * from Board b where b.bno > ?1", nativeQuery = true)
    @NativeQuery("select * from Board b where b.bno > ?1")
    List<Board> findByBnoGreaterThan(Long bno);

}
