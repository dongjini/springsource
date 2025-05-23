package com.example.movie.repository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.movie.entity.Member;
import com.example.movie.entity.MemberRole;
import com.example.movie.entity.Movie;
import com.example.movie.entity.MovieImage;
import com.example.movie.entity.Review;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepositoey movieRepositoey;

    @Autowired
    private MovieImageRepository movieImagerRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 영화 삽입
    @Test
    public void insertMovieTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie" + i)
                    .build();
            movieRepositoey.save(movie);

            // 임의의 이미지
            int count = (int) (Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .ord(j)
                        .imgName("test" + j + ".jpg")
                        .movie(movie)
                        .build();

                // movie.addImage(movieImage);
                movieImagerRepository.save(movieImage);
            }

        });
    }

    // 멤버 삽입
    @Test
    public void memberInsertTest() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@gmail.com")
                    .password(passwordEncoder.encode("1111"))
                    .memberRole(MemberRole.MEMBER)
                    .nickname("viewer" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    // 리뷰 삽입
    @Test
    public void reviewInsertTest() {
        // 리뷰 200개 / 영화 100 무작위로 추출 / 멤버 무작위 추출
        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 멤버 아이디 무작위
            Long mid = (long) (Math.random() * 20) + 1;

            // 영화 아이디 무작위
            Long mno = (long) (Math.random() * 100) + 1;

            Review review = Review.builder()
                    .grade((int) (Math.random() * 5) + 1)
                    .text("이 영화에 대한 느낌은 " + i)
                    .member(Member.builder().mid(mid).build())
                    .movie(Movie.builder().mno(mno).build())
                    .build();

            reviewRepository.save(review);

        });
    }

    // list
    @Test
    public void listTest() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Object[]> result = movieImagerRepository.getTotalList(null, null, pageable);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void getMovieTest() {
        List<Object[]> result = movieImagerRepository.getMovieRow(2L);
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

        // Movie movie = (Movie) result.get(0)[0];
        // MovieImage movieImage = (MovieImage) result.get(0)[1];
        // Long cnt = (Long) result.get(0)[2];
        // Double avg = (Double) result.get(0)[3];

        // Movie movie = (Movie) result.get(1)[0];
        // MovieImage movieImage = (MovieImage) result.get(1)[1];
        // Long cnt = (Long) result.get(1)[2];
        // Double avg = (Double) result.get(1)[3];

        // Movie movie = (Movie) result.get(2)[0];
        // MovieImage movieImage = (MovieImage) result.get(2)[1];
        // Long cnt = (Long) result.get(2)[2];
        // Double avg = (Double) result.get(2)[3];

    }

}
