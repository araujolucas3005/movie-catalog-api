package com.moviecatalog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "companies")
public class Company implements BaseModel<Company> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
	@SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "id_seq_company", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Movie> movies = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name != null ? name : this.name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		if (movies != null) {
			movies.forEach(movie -> movie.setCompany(this));
			this.movies = movies;
		}
	}
	
	public void addMovie(Movie movie) {
		if (movie != null) {
			movie.setCompany(this);
			this.movies.add(movie);
		}
	}
	
	public void addMovies(Set<Movie> movies) {
		if (movies != null) {
			movies.forEach(movie -> movie.setCompany(this));
			this.movies.addAll(movies);
		}
	}
	
	public void update(Company source) {
		if (source != null) {
			this.setMovies(source.getMovies());
			this.setName(source.getName());
		}
	}

}
