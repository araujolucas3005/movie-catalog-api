package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Movie;

public interface MovieService extends BaseService<Movie> {
	
	public ResponseEntity<Object> removeOldests(Integer quantity) throws Exception;
	
	public Object findAllAsLinkedList() throws JsonMappingException, JsonProcessingException;

}
