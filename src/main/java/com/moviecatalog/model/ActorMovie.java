package com.moviecatalog.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "actors_movies")
public class ActorMovie {
	
	@EmbeddedId
	private ActorMovieId id = new ActorMovieId();
	
	@ManyToOne
    @MapsId("actorId")
	@JoinColumn(name = "actor_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Actor actor;
 
    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Movie movie;
    
    public ActorMovie() {}

	public ActorMovie(Actor actor, Movie movie) {
		this.actor = actor;
		this.movie = movie;
	}

	public ActorMovieId getId() {
		return id;
	}

	public void setId(ActorMovieId id) {
		this.id = id;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		id.setActorId(actor.getId());
		this.actor = actor;
	}

	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		id.setMovieId(movie.getId());
		this.movie = movie;
	}
	

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ActorMovie that = (ActorMovie) o;
        return Objects.equals(actor, that.actor) &&
               Objects.equals(movie, that.movie);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(actor, movie);
    }

}
