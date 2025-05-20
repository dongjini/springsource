package com.example.movie.repository.total;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

public interface MovieImageReviewRepository {

    Page<Object[]> getTotalList(String type, String keyword, Pageable pageable);

    List<Object[]> getMovieRow(Long mno);

}
