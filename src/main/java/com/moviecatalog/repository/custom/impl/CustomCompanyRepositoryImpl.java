package com.moviecatalog.repository.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.custom.structures.impl.SinglyLinkedList;
import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.custom.CustomCompanyRepository;

public class CustomCompanyRepositoryImpl implements CustomCompanyRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public LinkedListInter<Movie> findAllMovies(Integer id) {
		String query = "select * from movies m where m.company_id = " + id;

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
			movies.addLast(movie);
		}

		return movies;
	}

	@Override
	public LinkedListInter<Company> findAllAsLinkedList() {
		String query = "select * from companies";

		LinkedListInter<Company> companies = new SinglyLinkedList<>();

		SqlRowSet rs = jdbcTemplate.queryForRowSet(query);

		while (rs.next()) {
			Company company = new Company();
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			companies.addLast(company);
		}

		return companies;
	}

}
