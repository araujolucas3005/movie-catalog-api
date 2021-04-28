package com.moviecatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Genre;
import com.moviecatalog.service.GenreService;

@RestController
public class GenreController {
	
	@Autowired
	private GenreService genreServ;
	
	@GetMapping("/genres")
	public Object index() throws JsonMappingException, JsonProcessingException {
		return genreServ.findAllAsLinkedList();
	}
	
	@GetMapping("/genre/{id}")
	public ResponseEntity<Genre> show(@PathVariable Integer id) {
		return genreServ.findById(id);
	}
	
	@GetMapping("/genre/{id}/subgenres")
	public ResponseEntity<Object> findAllSubgenres(@PathVariable Integer id) throws JsonMappingException, JsonProcessingException {
		return genreServ.findAllSubgenres(id);
	}
	
	@GetMapping("/genre/{id}/movies")
	public ResponseEntity<Object> findAllMovies(@PathVariable Integer id) throws JsonMappingException, JsonProcessingException {
		return genreServ.findAllMovies(id);
	}
	
	@PostMapping("/genre")
	public ResponseEntity<Genre> save(@RequestBody Genre genre) {
		return genreServ.save(genre);
	}
	
	@PutMapping("/genre/{id}")
	public ResponseEntity<Genre> save(@PathVariable Integer id, @RequestBody Genre genre) {
		return genreServ.update(id, genre);
	}
	
	@DeleteMapping("/genre/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return genreServ.delete(id);
	}

}
