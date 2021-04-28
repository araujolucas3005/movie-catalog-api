package com.moviecatalog.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.moviecatalog.custom.structures.StackInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedStack;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomMovieRepository;

public class CustomMovieRepositoryImpl implements CustomMovieRepository {
	
	@Autowired JdbcTemplate jdbcTemplate;

	@Override
	public StackInter<Movie> findOldestsAsStack() {
		String query = "select * from movies m order by m.release_date desc";
		
		StackInter<Movie> movies = new SinglyLinkedStack<>();
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
		
		while (rs.next()) {
			Movie movie = new Movie();
			movie.setCompanyId(rs.getInt("company_id"));
			movie.setGenreId(rs.getInt("genre_id"));
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("name"));
			movie.setReleaseDate(rs.getDate("release_date"));
			movie.setSynopsis(rs.getString("synopsis"));
			movies.push(movie);
		}
		
		return movies;
	}

}
