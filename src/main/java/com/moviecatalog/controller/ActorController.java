package com.moviecatalog.controller;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.service.ActorService;
import com.moviecatalog.util.IdForAssociation;

@RestController
public class ActorController {

	@Autowired
	private ActorService actorServ;

	@GetMapping("/actors")
	public Object index(@RequestParam(defaultValue = "id") String sortBy)
			throws JsonMappingException, JsonProcessingException, AttributeNotFoundException {
		return actorServ.findAllAndSort(sortBy);
	}

	@GetMapping("/actor/{id}")
	public ResponseEntity<Actor> show(@PathVariable Integer id) {
		return actorServ.findById(id);
	}

	@GetMapping("/actor/{id}/movies")
	public ResponseEntity<Object> findAllMovies(@PathVariable Integer id)
			throws JsonMappingException, JsonProcessingException {
		return actorServ.findAllMovies(id);
	}

	@PostMapping("/actor")
	public ResponseEntity<Actor> save(@RequestBody Actor actor) {
		return actorServ.save(actor);
	}

	@PostMapping("/actor/{id}/add/movie")
	public ResponseEntity<ActorMovie> save(@PathVariable Integer id, @RequestBody IdForAssociation movieId) {
		return actorServ.addMovie(id, movieId);
	}

	@PutMapping("/actor/{id}")
	public ResponseEntity<Actor> save(@PathVariable Integer id, @RequestBody Actor actor) {
		return actorServ.update(id, actor);
	}

	@DeleteMapping("/actor/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return actorServ.delete(id);
	}

	@DeleteMapping("/actor/{actorId}/movie/{movieId}")
	public ResponseEntity<Void> destroy(@PathVariable Integer actorId, @PathVariable Integer movieId) {
		return actorServ.removeMovie(actorId, movieId);
	}

}
