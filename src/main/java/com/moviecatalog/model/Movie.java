package com.moviecatalog.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movies")
public class Movie implements BaseModel<Movie> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIE")
	@SequenceGenerator(name = "SEQ_MOVIE", sequenceName = "id_seq_movie", allocationSize = 1)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String synopsis;
	
	private String posterPath;
	
	@Column(nullable = false)
	private Date releaseDate;
	
	@ManyToOne(optional = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Company company;

	@ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Actor> actors = new HashSet<>();
	
	@ManyToOne(optional = false)
	@JsonIgnoreProperties(value = {"subgenres", "parentGenre", "movies"})
	private Genre genre;

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

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis != null ? synopsis : this.synopsis;
	}

	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath != null ? posterPath : this.posterPath;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate != null ? releaseDate : this.releaseDate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company != null ? company : this.company;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors != null ? actors : this.actors;
	}

	public void addActor(Actor actor) {
		if (actor != null) {
			actor.setMovie((Movie) this);
			this.actors.add(actor);
		}
	}

	public void addActors(Set<Actor> actors) {
		if (actors != null) {
			actors.forEach(actor -> actor.setMovies((Set<Movie>) this));
			this.actors.addAll(actors);
		}
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		if (genre != null) {
			this.genre = genre;
		}
	}

	public void update(Movie source) {
		if (source != null) {
			this.setCompany(source.getCompany());
			this.setName(source.getName());
			this.setPosterPath(source.getPosterPath());
			this.setReleaseDate(source.getReleaseDate());
			this.setSynopsis(source.getSynopsis());
			this.setActors(source.getActors());
		}
	}
	
}
