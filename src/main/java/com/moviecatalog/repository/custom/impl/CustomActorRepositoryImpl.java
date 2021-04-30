package com.moviecatalog.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedList;
import com.moviecatalog.model.Actor;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomActorRepository;

public class CustomActorRepositoryImpl implements CustomActorRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public LinkedListInter<Actor> findAllAsLinkedList() {
		String query = "select * from actors";

		LinkedListInter<Actor> actors = new SinglyLinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);

		while (rs.next()) {
			Actor actor = new Actor();
			actor.setId(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actors.add(actor);
		}

		return actors;
	}

	@Override
	public LinkedListInter<Movie> findAllMovies(Integer id) {
		String query = "select * from movies m where m.id in "
				+ "(select movie_id from actors_movies am where am.actor_id = " + 
				id + 
				")";
		
		LinkedListInter<Movie> movies = new SinglyLinkedList<>();

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

		return movies;
	}

}
