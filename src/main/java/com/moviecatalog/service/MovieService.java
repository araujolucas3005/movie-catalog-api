package com.moviecatalog.service;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.model.Movie;
import com.moviecatalog.util.IdForAssociation;

public interface MovieService extends BaseService<Movie> {
	
	public ResponseEntity<Object> removeOldests(Integer quantity) throws Exception;
	
	public ResponseEntity<ActorMovie> addActor(Integer movieId, IdForAssociation actorId);
	
	public ResponseEntity<Void> removeActor(Integer movieId, Integer actorId);
	
	public ResponseEntity<Object> findCast(Integer id) throws JsonMappingException, JsonProcessingException;
	
	public Object findAllAsLinkedList() throws JsonMappingException, JsonProcessingException;

}
