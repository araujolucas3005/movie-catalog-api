package com.moviecatalog.controller;

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
import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.model.Movie;
import com.moviecatalog.service.MovieService;
import com.moviecatalog.util.IdForAssociation;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieServ;
	
	@GetMapping("/movies")
	public Object index() throws JsonMappingException, JsonProcessingException {
		return movieServ.findAllAsLinkedList();
	}
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> show(@PathVariable Integer id) {
		return movieServ.findById(id);
	}
	
	@GetMapping("/movie/{id}/cast")
	public ResponseEntity<Object> findCast(@PathVariable Integer id) throws JsonMappingException, JsonProcessingException {
		return movieServ.findCast(id);
	}
	
	@PostMapping("/movie")
	public ResponseEntity<Movie> save(@RequestBody Movie movie) {
		return movieServ.save(movie);
	}
	
	@PostMapping("/movie/{id}/add/actor")
	public ResponseEntity<ActorMovie> save(@PathVariable Integer id, @RequestBody IdForAssociation actorId) {
		return movieServ.addActor(id, actorId);
	}
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> update(@PathVariable Integer id, @RequestBody Movie movie) {
		return movieServ.update(id, movie);
	}
	
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return movieServ.delete(id);
	}
	
	@DeleteMapping("/movie/remove/oldests")
	public ResponseEntity<Object> removeOldests(@RequestParam(defaultValue = "0") Integer quantity) throws Exception {
		return movieServ.removeOldests(quantity);
	}
	
	@DeleteMapping("/movie/{movieId}/actor/{actorId}")
	public ResponseEntity<Void> removeActor(@PathVariable Integer movieId, @PathVariable Integer actorId) {
		return movieServ.removeActor(movieId, actorId);
	}

}
