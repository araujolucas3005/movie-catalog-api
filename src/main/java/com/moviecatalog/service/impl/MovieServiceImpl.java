package com.moviecatalog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.StackInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedList;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.model.ActorMovieId;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.ActorMovieRepository;
import com.moviecatalog.repository.ActorRepository;
import com.moviecatalog.repository.MovieRepository;
import com.moviecatalog.service.MovieService;
import com.moviecatalog.util.IdForAssociation;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, MovieRepository> implements MovieService {

	@Autowired
	private ActorRepository actorRepo;

	@Autowired
	private MovieRepository movieRepo;

	@Autowired
	private ActorMovieRepository actorMovieRepo;

	@Override
	public ResponseEntity<Object> removeOldests(Integer quantity) throws Exception {
		StackInter<Movie> movies = movieRepo.findAllAsStackOrderedByDateDesc();

		LinkedListInter<Movie> deletedMovies = new SinglyLinkedList<>();

		while (!movies.isEmpty() && quantity > 0) {
			Movie deletedMovie = movies.pop();
			deletedMovies.addLast(deletedMovie);
			movieRepo.deleteById(deletedMovie.getId());
			quantity--;
		}

		return ResponseEntity.ok(deletedMovies.formatToJSONObject());
	}

	@Override
	public ResponseEntity<ActorMovie> addActor(Integer movieId, IdForAssociation actorId) {
		Optional<Actor> actor = actorRepo.findById(actorId.getId());
		Optional<Movie> movie = movieRepo.findById(movieId);

		if (!actor.isPresent() || !movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		ActorMovie association = new ActorMovie(actor.get(), movie.get());
		association = actorMovieRepo.save(association);

		return ResponseEntity.ok().body(association);
	}

	@Override
	public ResponseEntity<Void> removeActor(Integer movieId, Integer actorId) {
		if (!actorRepo.existsById(actorId) || !movieRepo.existsById(movieId)) {
			return ResponseEntity.notFound().build();
		}

		actorMovieRepo.deleteById(new ActorMovieId(actorId, movieId));

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Object> findCast(Integer id) throws JsonMappingException, JsonProcessingException {
		Optional<Movie> movie = movieRepo.findById(id);

		if (!movie.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		LinkedListInter<Actor> cast = movieRepo.findCast(id);
		return ResponseEntity.ok(cast.formatToJSONObject());
	}

}
