package com.moviecatalog.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "movies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movie implements BaseModel<Movie> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MOVIE")
	@SequenceGenerator(name = "SEQ_MOVIE", sequenceName = "id_seq_movie", allocationSize = 1)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String synopsis;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	@Column(nullable = false)
	private Date releaseDate;
	
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Company company;
	
	@ManyToOne(optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Genre genre;
	
	@Transient
	private Integer companyId;
	
	@Transient
	private Integer genreId;
	
	@PostUpdate
	private void onPostUpdate() {
		this.genreId = genre.getId();
		this.companyId = company.getId();
	}

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
			this.setReleaseDate(source.getReleaseDate());
			this.setSynopsis(source.getSynopsis());
			this.setGenre(source.getGenre());
		}
	}

	public Integer getCompanyId() {
		return companyId == null ? company.getId() : companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getGenreId() {
		return genreId == null ? genre.getId() : genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	
	public String toJSONString() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		return "{" +
				"\"id\":" + this.id +
				",\"name\":" + '"' + this.name + '"' +
				",\"synopsis\":" + '"' + this.synopsis + '"' +
				",\"releaseDate\":" + '"' + formatter.format(this.releaseDate) + '"' +
				",\"companyId\":" + this.companyId +
				",\"genreId\":" + this.genreId +
				"}";
	}
	
}
