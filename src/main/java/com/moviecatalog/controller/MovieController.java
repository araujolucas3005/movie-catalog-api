package com.moviecatalog.controller;

import java.util.List;

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

import com.moviecatalog.model.Movie;
import com.moviecatalog.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieServ;
	
	@GetMapping("/movies")
	public List<Movie> index() {
		return movieServ.findAll();
	}
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> show(@PathVariable Integer id) {
		return movieServ.findById(id);
	}
	
	@PostMapping("/movie")
	public ResponseEntity<Movie> save(@RequestBody Movie company) {
		return movieServ.save(company);
	}
	
	@PutMapping("/movie/{id}")
	public ResponseEntity<Movie> save(@PathVariable Integer id, @RequestBody Movie movie) {
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

}
