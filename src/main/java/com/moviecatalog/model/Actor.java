package com.moviecatalog.model;

import javax.management.AttributeNotFoundException;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "actors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Actor implements BaseModel<Actor> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTOR")
	@SequenceGenerator(name = "SEQ_ACTOR", sequenceName = "id_seq_actor", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	public static final String BY_FIRSTNAME = "firstName";
	
	public static final String BY_LASTNAME = "lastName";

	public static final String BY_ID = "id";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName != null ? firstName : this.firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName != null ? lastName : this.lastName;
	}

	@Override
	public void update(Actor source) {
		if (source != null) {
			this.setFirstName(source.getFirstName());
			this.setLastName(source.getLastName());
		}
	}

	public Integer compareTo(Actor other, String attribute) throws AttributeNotFoundException {
		switch (attribute) {
			case BY_FIRSTNAME:
				return firstName.compareTo(other.firstName);
			case BY_ID:
				return id.compareTo(other.id);
			case BY_LASTNAME:
				return lastName.compareTo(other.lastName);
		}
		throw new AttributeNotFoundException("Esse atributo não existe");
	}

	@Override
	public String toJSONString() {
		return "{" + "\"id\":" + this.id + ",\"firstName\":" + '"' + this.firstName + '"' + ",\"lastName\":" + '"'
				+ this.lastName + '"' + "}";
	}

}