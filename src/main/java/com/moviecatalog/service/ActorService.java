package com.moviecatalog.service;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface ActorService extends BaseService<Actor> {

    public ResponseEntity<Set<Movie>> findAllMovies(Integer id);

}
