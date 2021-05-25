package com.moviecatalog.repository;

import com.moviecatalog.model.Genre;
import com.moviecatalog.repository.custom.CustomGenreRepository;

public interface GenreRepository extends BaseRepository<Genre, Integer>, CustomGenreRepository {
	
}
