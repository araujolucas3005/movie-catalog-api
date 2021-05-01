package com.moviecatalog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "companies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company implements BaseModel<Company> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
	@SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "id_seq_company", allocationSize = 1)
	private Integer id;

	@Column(nullable = false)
	private String name;

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
	
	public void update(Company source) {
		if (source != null) {
			this.setName(source.getName());
		}
	}

	@Override
	public String toJSONString() {
		return "{" +
				"\"id\":" + this.id +
				",\"name\":" + '"' + this.name + '"' +
				"}";
	}

}
