package com.moviecatalog.model;

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

    public void update(Actor source) {
        if (source != null) {
            this.setFirstName(source.getFirstName());
            this.setLastName(source.getLastName());
        }
    }

	@Override
	public String toJSONString() {
		return "{" +
				"\"id\":" + this.id +
				",\"firstName\":" + '"' + this.firstName + '"' +
				",\"lastName\":" + '"' + this.lastName + '"' +
				"}";
	}
}