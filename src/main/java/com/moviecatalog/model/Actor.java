package com.moviecatalog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
public class Actor implements BaseModel<Actor> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACTOR")
    @SequenceGenerator(name = "SEQ_ACTOR", sequenceName = "id_seq_actor", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany
    @JoinTable(name="movies_actors", joinColumns=
            {@JoinColumn(name="actor_id")}, inverseJoinColumns=
            {@JoinColumn(name="movie_id")})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Movie> movies = new HashSet<>();

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

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        if (movies != null) {
            movies.forEach(movie -> movie.setActors((Set<Actor>) this));
            this.movies = movies;
        }
    }

    public void setMovie(Movie movie) {
        Set<Movie> movies1 = new HashSet<>();
        movies1.add(movie);
        this.movies = movies1 != null ? movies1 : this.movies;
    }

    public void update(Actor source) {
        if (source != null) {
            this.setFirstName(source.getFirstName());
            this.setLastName(source.getLastName());
        }
    }
}
