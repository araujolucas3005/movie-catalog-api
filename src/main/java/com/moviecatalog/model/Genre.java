package com.moviecatalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "genres")
public class Genre implements BaseModel<Genre> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENRE")
	@SequenceGenerator(name = "SEQ_GENRE", sequenceName = "id_seq_genre", allocationSize = 1)
	private Integer id;

	private String name;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Genre parentGenre;

	@Column(name = "parent_genre_id", insertable = false, updatable = false)
	private Integer parentGenreId;

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

	public Genre getParentGenre() {
		return parentGenre;
	}

	public void setParentGenre(Genre parentGenre) {
		this.parentGenre = parentGenre != null ? parentGenre : this.parentGenre;
	}

	public int compareTo(Genre other) {
		return this.id != null ? id.compareTo(other.getId()) : 1;
	}

	public void update(Genre source) {
		if (source != null) {
			this.setName(source.getName());
			this.setParentGenre(source.getParentGenre());
		}
	}

	public Integer getParentGenreId() {
		return parentGenreId;
	}

	public void setParentGenreId(Integer parentGenreId) {
		this.parentGenreId = parentGenreId;
	}

	@Override
	public String toJSONString() {
		return "{" +
				"\"id\":" + this.id +
				",\"name\":" + '"' + this.name + '"' +
				",\"parentGenreId\":" + this.parentGenreId +
				"}";
	}

}
