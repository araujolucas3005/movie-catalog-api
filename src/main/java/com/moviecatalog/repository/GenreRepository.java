package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.Genre;
import com.moviecatalog.repository.custom.CustomGenreRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer>, CustomGenreRepository {
	
}
