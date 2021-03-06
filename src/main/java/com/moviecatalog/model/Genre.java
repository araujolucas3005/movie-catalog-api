package com.moviecatalog.model;

import javax.management.AttributeNotFoundException;
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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "genres")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Genre implements BaseModel<Genre> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GENRE")
	@SequenceGenerator(name = "SEQ_GENRE", sequenceName = "id_seq_genre", allocationSize = 1)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Genre parentGenre;

	@Transient
	private Integer parentGenreId;
	
	public static final String BY_ID = "id";
	
	public static final String BY_NAME = "name";
	
	public static final String BY_PARENT_GENRE = "parentGenre";
	
	@PostUpdate
	private void onPostUpdate() {
		this.parentGenreId = parentGenre != null ? parentGenre.getId() : null;
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

	public Genre getParentGenre() {
		return parentGenre;
	}

	public void setParentGenre(Genre parentGenre) {
		this.parentGenre = parentGenre != null ? parentGenre : this.parentGenre;
	}

	public Integer getParentGenreId() {
		if ((parentGenreId == null || parentGenreId == 0) && parentGenre == null) {
			return null;
		}
		return parentGenreId == null ? parentGenre.getId() : parentGenreId;
	}

	public void setParentGenreId(Integer parentGenreId) {
		this.parentGenreId = parentGenreId;
	}
	
	public void update(Genre source) {
		if (source != null) {
			this.setName(source.getName());
			this.setParentGenre(source.getParentGenre());
		}
	}
	
	@Override
	public Integer compareTo(Genre other, String attribute) throws AttributeNotFoundException {
		switch (attribute) {
			case BY_NAME:
				return name.compareTo(other.name);
			case BY_ID:
				return id.compareTo(other.id);
			case BY_PARENT_GENRE:
				return parentGenreId.compareTo(other.parentGenreId);
		}
		throw new AttributeNotFoundException("Esse atributo n??o existe");
	}

	@Override
	public String toJSONString() {
		String result = 
				"{" +
				"\"id\":" + this.id +
				",\"name\":" + '"' + this.name + '"';
		
		if (this.parentGenreId != 0 || this.parentGenreId == null) {
			result += ",\"parentGenreId\":" + this.getParentGenreId();
		}
		
		result += "}";
		
		return result;
	}

}
