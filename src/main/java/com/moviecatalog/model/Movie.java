package com.moviecatalog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
		}
	}
	
}
