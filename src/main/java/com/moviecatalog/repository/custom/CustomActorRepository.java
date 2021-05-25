package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;

public interface CustomActorRepository extends BaseCustomRepository<Actor> {
	
	public LinkedListInter<Movie> findAllMovies(Integer id);

}
