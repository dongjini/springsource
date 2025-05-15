package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie.entity.Movie;

public interface MovieRepositoey extends JpaRepository<Movie, Long> {

}
