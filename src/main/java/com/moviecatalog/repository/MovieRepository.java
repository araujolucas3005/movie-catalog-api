package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomMovieRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer>, CustomMovieRepository {
	
}
