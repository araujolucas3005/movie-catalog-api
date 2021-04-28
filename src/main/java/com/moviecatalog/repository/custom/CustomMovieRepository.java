package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.StackInter;
import com.moviecatalog.model.Movie;

public interface CustomMovieRepository {
	
	public StackInter<Movie> findOldestsAsStack();

}
