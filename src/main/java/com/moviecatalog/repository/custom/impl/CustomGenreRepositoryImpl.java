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
		String query = "select * from genres g where g.parent_genre_id = " + id;

		LinkedListInter<Genre> subgenres = new SinglyLinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);

		while (rs.next()) {
			Genre subgenre = new Genre();
			subgenre.setId(rs.getInt("id"));
			subgenre.setName(rs.getString("name"));
			subgenre.setParentGenreId(rs.getInt("parent_genre_id"));
			subgenres.add(subgenre);
		}

		return subgenres;
	}

	@Override
	public LinkedListInter<Movie> findAllMovies(Integer id) {
		
		LinkedListInter<Movie> movies = new SinglyLinkedList<>();
		
		String firstQuery = "select * from movies where genre_id = " + id;
		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(firstQuery);
		
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

		String secondQuery = "select g.id from genres g where parent_genre_id = " + id;

		SqlRowSet rsOut = jdbcTemplate.queryForRowSet(secondQuery);
		
		while (rsOut.next()) {
			Integer subgenreId = rsOut.getInt("id");
			
			String query = "select * from movies where genre_id = " + subgenreId;
			
			SqlRowSet rsIn = jdbcTemplate.queryForRowSet(query);
			
			while (rsIn.next()) {
				Movie movie = new Movie();
				movie.setCompanyId(rsIn.getInt("company_id"));
				movie.setGenreId(rsIn.getInt("genre_id"));
				movie.setId(rsIn.getInt("id"));
				movie.setName(rsIn.getString("name"));
				movie.setReleaseDate(rsIn.getDate("release_date"));
				movie.setSynopsis(rsIn.getString("synopsis"));
				movies.add(movie);
			}
		}

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

}
