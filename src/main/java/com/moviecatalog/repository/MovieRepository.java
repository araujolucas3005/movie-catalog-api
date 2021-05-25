package com.moviecatalog.repository;

import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomMovieRepository;

public interface MovieRepository extends BaseRepository<Movie, Integer>, CustomMovieRepository {
	
}
