package com.moviecatalog.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedList;
import com.moviecatalog.model.Genre;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomGenreRepository;

public class CustomGenreRepositoryImpl implements CustomGenreRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public LinkedListInter<Genre> findAllSubgenres(Integer id) {

		LinkedListInter<Genre> subgenres = new SinglyLinkedList<>();

		getSubgenres(subgenres, id);

		return subgenres;
	}

	@Override
	public LinkedListInter<Movie> findAllMovies(Integer id) {
		
		LinkedListInter<Movie> movies = new SinglyLinkedList<>();
		
		getMovies(movies, id);

		return movies;
	}

	@Override
	public LinkedListInter<Genre> findAllAsLinkedList() {
		String query = "select * from genres";

		LinkedListInter<Genre> genres = new SinglyLinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);

		while (rs.next()) {
			Genre genre = new Genre();
			genre.setId(rs.getInt("id"));
			genre.setName(rs.getString("name"));
			genre.setParentGenreId(rs.getInt("parent_genre_id"));
			genres.add(genre);
		}

		return genres;
	}
	
	private void getSubgenres(LinkedListInter<Genre> subgenres, Integer id) {
		
		String query = "select * from genres g where g.parent_genre_id = " + id;
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
		
		while (rs.next()) {
			Genre subgenre = new Genre();
			subgenre.setId(rs.getInt("id"));
			subgenre.setName(rs.getString("name"));
			subgenre.setParentGenreId(rs.getInt("parent_genre_id"));
			subgenres.add(subgenre);
			
			getSubgenres(subgenres, subgenre.getId());
		}
		
	}
	
	private void getMovies(LinkedListInter<Movie> movies, Integer id) {
		
		String query = "select * from movies m where m.genre_id = " + id;
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);
		
		while (rs.next()) {
			Movie movie = new Movie();
			movie.setCompanyId(rs.getInt("company_id"));
			movie.setGenreId(rs.getInt("genre_id"));
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("name"));
			movie.setReleaseDate(rs.getDate("release_date"));
			movie.setSynopsis(rs.getString("synopsis"));
			movies.add(movie);
		}
		
		query = "select id from genres g where g.parent_genre_id = " + id;
		
		rs = jdbcTemplate.queryForRowSet(query);
		
		while (rs.next()) {
			
			Integer subgenreId = rs.getInt("id");
			
			getMovies(movies, subgenreId);
			
		}
		
	}

}
