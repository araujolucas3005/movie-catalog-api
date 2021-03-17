package com.moviecatalog.service;

import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;

public interface CompanyService extends BaseService<Company> {
	
	public ResponseEntity<Set<Movie>> findAllMovies(Integer id);

}
