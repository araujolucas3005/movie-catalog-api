package com.moviecatalog.service;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Genre;

public interface GenreService extends BaseService<Genre> {
	
	public ResponseEntity<Object> findAllSubgenres(Integer id, String attribute, String order) 
			throws JsonMappingException, JsonProcessingException, AttributeNotFoundException;
	
	public ResponseEntity<Object> findAllMovies(Integer id, String attribute, String order) 
			throws JsonMappingException, JsonProcessingException, AttributeNotFoundException;

}
