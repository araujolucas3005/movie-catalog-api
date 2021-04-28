package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.moviecatalog.model.Movie;

public interface MovieService extends BaseService<Movie> {
	
	public ResponseEntity<Object> removeOldests(Integer quantity) throws Exception;

}
