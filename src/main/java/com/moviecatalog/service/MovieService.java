package com.moviecatalog.service;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface MovieService extends BaseService<Movie> {

    public ResponseEntity<Set<Actor>> findAllActors(Integer id);

    public ResponseEntity<Void> addActor(Integer id, Actor actor);

    public ResponseEntity<Void> addActors(Integer id, Set<Actor> actors);

}
