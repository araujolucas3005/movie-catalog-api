package com.moviecatalog.service;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface ActorService extends BaseService<Actor> {

    public ResponseEntity<Set<Movie>> findAllMovies(Integer id);

}
