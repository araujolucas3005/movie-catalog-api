package com.moviecatalog.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;

public interface GenreService extends BaseService<Genre> {
	
	public List<Genre> findAllParents();
	
	public ResponseEntity<Set<Movie>> findAllMovies(Integer id);

}
