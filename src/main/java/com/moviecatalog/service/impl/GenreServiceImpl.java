package com.moviecatalog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.GenreRepository;
import com.moviecatalog.service.GenreService;

@Service
public class GenreServiceImpl extends BaseServiceImpl<Genre, GenreRepository> implements GenreService {
	
	@Autowired private GenreRepository genreRepo;

	public List<Genre> findAllParents() {
		return genreRepo.findAllParents();
	}

	public ResponseEntity<Set<Movie>> findAllMovies(Integer id) {
		Optional<Genre> genre = genreRepo.findById(id);
		if (!genre.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(genre.get().getMovies());
	}

	public ResponseEntity<Void> addSubgenre(Integer id, Genre subgenre) {
		Optional<Genre> genre = genreRepo.findById(id);
		if (!genre.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		genre.get().addSubgenre(subgenre);
		genreRepo.save(genre.get());
		return ResponseEntity.noContent().build();
	}
	
}
