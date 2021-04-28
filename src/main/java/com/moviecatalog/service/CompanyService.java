package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Company;

public interface CompanyService extends BaseService<Company> {
	
	public ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException;
	
//	public ResponseEntity<Void> addMovie(Integer id, Movie movie);
//	
//	public ResponseEntity<Void> addMovies(Integer id, Set<Movie> movies);

}
