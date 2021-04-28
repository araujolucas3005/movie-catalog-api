package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;

public interface CustomGenreRepository {
	
	public LinkedListInter<Genre> findAllSubgenres(Integer id);
	
	public LinkedListInter<Movie> findAllMovies(Integer id);
	
	public LinkedListInter<Genre> findAllAsLinkedList();

}
