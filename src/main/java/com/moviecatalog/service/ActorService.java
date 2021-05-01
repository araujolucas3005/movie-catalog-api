package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Actor;
import com.moviecatalog.util.IdForAssociation;

public interface ActorService extends BaseService<Actor> {
	
	public Object findAllAsLinkedList() throws JsonMappingException, JsonProcessingException;

	ResponseEntity<Object> addMovie(Integer actorId, IdForAssociation movieId);
	
	ResponseEntity<Void> removeMovie(Integer actorId, Integer movieId);
	
	ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException;

}
