package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Genre;

public interface GenreService extends BaseService<Genre> {
	
	public ResponseEntity<Object> findAllSubgenres(Integer id) throws JsonMappingException, JsonProcessingException;
	
	public ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException;
		
	public Object findAllAsLinkedList() throws JsonMappingException, JsonProcessingException;

}
