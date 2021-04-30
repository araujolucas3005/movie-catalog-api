package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.StackInter;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;

public interface CustomMovieRepository {
	
	public StackInter<Movie> findAllAsStackOrderedByDateDesc();
	
	public LinkedListInter<Actor> findCast(Integer id);
	
	public LinkedListInter<Movie> findAllAsLinkedList();

}
