package com.moviecatalog.repository.custom.impl;

import com.moviecatalog.custom.structures.DoublyLinkedListInter;
import com.moviecatalog.custom.structures.QueueInter;
import com.moviecatalog.custom.structures.QuickSortInter;
import com.moviecatalog.custom.structures.impl.DoublyLinkedList;
import com.moviecatalog.custom.structures.impl.Queue;
import com.moviecatalog.custom.structures.impl.QuickSort;
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
	public DoublyLinkedListInter<Movie> findAllMovies(Integer id) {
		String query = "select * from movies m where m.id in "
				+ "(select movie_id from actors_movies am where am.actor_id = " +
				id +
				")";

		SqlRowSet rsId = jdbcTemplate.queryForRowSet(query);

		QueueInter<Integer> idQueue = new Queue<>();
		while (rsId.next()) {
			idQueue.enqueue(rsId.getInt("id"));
		}

		int size = idQueue.size();

		int[] ids = new int[size];

		for (int i = 0; i < size; i++) {
			ids[i] = idQueue.dequeue();
		}

		QuickSortInter order = new QuickSort();
		order.sort(ids, 0, size - 1);

		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);

		DoublyLinkedListInter<Movie> movies = new DoublyLinkedList<>();

		while (rs.next()) {
			Movie movie = new Movie();
			movie.setCompanyId(rs.getInt("company_id"));
			movie.setGenreId(rs.getInt("genre_id"));
			movie.setId(rs.getInt("id"));
			movie.setName(rs.getString("name"));
			movie.setReleaseDate(rs.getDate("release_date"));
			movie.setSynopsis(rs.getString("synopsis"));
			movies.pushLast(movie);
		}

		DoublyLinkedListInter<Movie> linedMovies = new DoublyLinkedList<>();

		for (int i = 0; i < size; i++) {
			Movie movie = new Movie();
			linedMovies.pushLast(movies.searchPosition(ids[i]));
		}

		return linedMovies;
	}
}
