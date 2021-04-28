package com.moviecatalog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.GenreRepository;
import com.moviecatalog.service.GenreService;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, GenreRepository> implements GenreService {
	
	@Autowired private GenreRepository genreRepo;

	@Override
	public ResponseEntity<Object> findAllSubgenres(Integer id) throws JsonMappingException, JsonProcessingException {
		Optional<Genre> genre = genreRepo.findById(id);
		if (!genre.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LinkedListInter<Genre> subgenres = genreRepo.findAllSubgenres(id);
		return ResponseEntity.ok(subgenres.formatToJSONObject());
	}

	@Override
	public ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException {
		Optional<Genre> genre = genreRepo.findById(id);
		if (!genre.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LinkedListInter<Movie> movies = genreRepo.findAllMovies(id);
		return ResponseEntity.ok(movies.formatToJSONObject());
	}
	
}
