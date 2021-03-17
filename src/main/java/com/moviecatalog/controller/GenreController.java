package com.moviecatalog.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;
import com.moviecatalog.service.GenreService;

@RestController
public class GenreController {
	
	@Autowired
	private GenreService genreServ;
	
	@GetMapping("/genres")
	public List<Genre> index() {
		return genreServ.findAllParents();
	}
	
	@GetMapping("/genre/{id}")
	public ResponseEntity<Genre> show(@PathVariable Integer id) {
		return genreServ.findById(id);
	}
	
	@GetMapping("/genre/{id}/movies")
	public ResponseEntity<Set<Movie>> findAllMovies(@PathVariable Integer id) {
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
