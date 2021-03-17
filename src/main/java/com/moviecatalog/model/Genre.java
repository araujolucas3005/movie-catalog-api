package com.moviecatalog.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "genres")
public class Genre implements BaseModel<Genre>, Comparable<Genre> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENRE")
	@SequenceGenerator(name = "SEQ_GENRE", sequenceName = "id_seq_genre", allocationSize = 1)
	private Integer id;

	private String name;

	@ManyToOne
	@JsonBackReference
	private Genre parentGenre;

	@SortNatural
	@JsonManagedReference
	@OneToMany(mappedBy = "parentGenre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Genre> subgenres = new TreeSet<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
		if (name != null) {
			this.name = name;
		}
	}

	public Genre getParentGenre() {
		return parentGenre;
	}

	public void setParentGenre(Genre parentGenre) {
		if (parentGenre != null) {
			this.parentGenre = parentGenre;
		}
	}

	public Set<Genre> getSubgenres() {
		return subgenres;
	}

	public void setSubgenres(Set<Genre> subgenres) {
		if (subgenres != null) {
			subgenres.forEach(subgenre -> subgenre.setParentGenre(this));
			this.subgenres = subgenres;
		}
	}

	public Set<Movie> getMovies() {
		if (!this.subgenres.isEmpty()) {
			getMoviesFromChildren(this.subgenres);
		}
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public void addSubgenre(Genre subgenre) {
		if (subgenre != null) {
			subgenre.setParentGenre(this);
			this.subgenres.add(subgenre);
		}
	}

	public int compareTo(Genre other) {
		if (this.id != null) {
			return id.compareTo(other.getId());
		}
		return 1;
	}

	public void update(Genre source) {
		if (source != null) {
			this.setName(source.getName());
			this.setParentGenre(source.getParentGenre());
			this.setSubgenres(source.getSubgenres());
		}
	}

	private void getMoviesFromChildren(Set<Genre> subgenres) {
		if (!subgenres.isEmpty()) {
			subgenres.forEach(subgenre -> {
				this.movies.addAll(subgenre.getMovies());
				getMoviesFromChildren(subgenre.getSubgenres());
			});
		}
	}

}
