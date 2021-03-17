package com.moviecatalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.moviecatalog.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
	
	@Query("select g from Genre g where g.parentGenre is null order by id")
	public List<Genre> findAllParents();
	
}
