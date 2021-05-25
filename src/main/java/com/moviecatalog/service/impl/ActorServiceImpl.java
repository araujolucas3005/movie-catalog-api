package com.moviecatalog.service.impl;

import java.util.Optional;

import com.moviecatalog.custom.structures.DoublyLinkedListInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.model.ActorMovieId;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.ActorMovieRepository;
import com.moviecatalog.repository.ActorRepository;
import com.moviecatalog.repository.MovieRepository;
import com.moviecatalog.service.ActorService;
import com.moviecatalog.util.IdForAssociation;

@Service
public class ActorServiceImpl extends BaseServiceImpl<Actor, ActorRepository> implements ActorService {

	@Autowired
	private ActorRepository actorRepo;

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private ActorMovieRepository actorMovieRepo;

	@Override
	public ResponseEntity<ActorMovie> addMovie(Integer actorId, IdForAssociation movieId) {
		Optional<Actor> actor = actorRepo.findById(actorId);
		Optional<Movie> movie = movieRepo.findById(movieId.getId());

		if (!actor.isPresent() || !movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		ActorMovie association = new ActorMovie(actor.get(), movie.get());
		association = actorMovieRepo.save(association);

		return ResponseEntity.ok().body(association);
	}

	@Override
	public ResponseEntity<Void> removeMovie(Integer actorId, Integer movieId) {
		if (!actorRepo.existsById(actorId) || !movieRepo.existsById(movieId)) {
			return ResponseEntity.notFound().build();
		}

		actorMovieRepo.deleteById(new ActorMovieId(actorId, movieId));

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException {
		Optional<Actor> actor = actorRepo.findById(id);
		if (!actor.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		DoublyLinkedListInter<Movie> movies = actorRepo.findAllMovies(id);
		return ResponseEntity.ok(movies.formatToJSONObject());
	}

}
