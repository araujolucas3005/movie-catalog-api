package com.moviecatalog.service.impl;

import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.ActorRepository;
import com.moviecatalog.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public class ActorServiceImpl extends BaseServiceImpl<Actor, ActorRepository> implements ActorService {

    @Autowired private ActorRepository actRepo;

    public ResponseEntity<Set<Movie>> findAllMovies(Integer id) {
        Optional<Actor> actor = actRepo.findById(id);
        if (!actor.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actor.get().getMovies());
    }

}
