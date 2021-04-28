package com.moviecatalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.StackInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedList;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.MovieRepository;
import com.moviecatalog.service.MovieService;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, MovieRepository> implements MovieService {
	
	@Autowired private MovieRepository movieRepo;

	@Override
	public ResponseEntity<Object> removeOldests(Integer quantity) throws Exception {
		StackInter<Movie> movies = movieRepo.findAllAsStackOrderedByDateDesc();
		
		LinkedListInter<Movie> deletedMovies = new SinglyLinkedList<>();
		
		while (!movies.isEmpty() && quantity > 0) {
			Movie deletedMovie = movies.pop();
			deletedMovies.add(deletedMovie);
			movieRepo.deleteById(deletedMovie.getId());
			quantity--;
		}
		
		return ResponseEntity.ok(deletedMovies.formatToJSONObject());
	}

	@Override
	public Object findAllAsLinkedList() throws JsonMappingException, JsonProcessingException {
		return movieRepo.findAllAsLinkedList().formatToJSONObject();
	}

}
