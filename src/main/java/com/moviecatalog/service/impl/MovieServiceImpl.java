package com.moviecatalog.service.impl;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.MovieRepository;
import com.moviecatalog.service.MovieService;

import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, MovieRepository> implements MovieService {

    @Autowired
    private MovieRepository movieRepo;

    public ResponseEntity<Set<Actor>> findAllActors(Integer id) {
        Optional<Movie> movie = movieRepo.findById(id);
        if (!movie.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie.get().getActors());
    }

    public ResponseEntity<Void> addActor(Integer id, Actor actor) {
        Optional<Movie> movie = movieRepo.findById(id);
        if (!movie.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        movie.get().addActor(actor);
        movieRepo.save(movie.get());
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> addActors(Integer id, Set<Actor> actors) {
        Optional<Movie> movie = movieRepo.findById(id);
        if (!movie.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        movie.get().addActors(actors);
        movieRepo.save(movie.get());
        return ResponseEntity.noContent().build();
    }

}
