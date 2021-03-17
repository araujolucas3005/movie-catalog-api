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

import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;
import com.moviecatalog.service.CompanyService;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyService compServ;
	
	@GetMapping("/companies")
	public List<Company> index() {
		return compServ.findAll();
	}
	
	@GetMapping("/company/{id}")
	public ResponseEntity<Company> show(@PathVariable Integer id) {
		return compServ.findById(id);
	}
	
	@GetMapping("/company/{id}/movies")
	public ResponseEntity<Set<Movie>> findAllMovies(@PathVariable Integer id) {
		return compServ.findAllMovies(id);
	}
	
	@PostMapping("/company")
	public ResponseEntity<Company> save(@RequestBody Company company) {
		return compServ.save(company);
	}
	
	@PostMapping("/company/{id}/add_movie")
	public ResponseEntity<Void> addMovie(@PathVariable Integer id, @RequestBody Movie movie) {
		return compServ.addMovie(id, movie);
	}
	
	@PostMapping("/company/{id}/add_movies")
	public ResponseEntity<Void> addMovies(@PathVariable Integer id, @RequestBody Set<Movie> movies) {
		return compServ.addMovies(id, movies);
	}
	
	@PutMapping("/company/{id}")
	public ResponseEntity<Company> update(@PathVariable Integer id, @RequestBody Company company) {
		return compServ.update(id, company);
	}
	
	@DeleteMapping("/company/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return compServ.delete(id);
	}

}
