package com.moviecatalog.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActorMovieId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "actor_id")
	private Integer actorId;
	
	@Column(name = "movie_id")
	private Integer movieId;
	
	public ActorMovieId() {}

	public ActorMovieId(Integer actorId, Integer movieId) {
		this.actorId = actorId;
		this.movieId = movieId;
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
 
        if (o == null || getClass() != o.getClass())
            return false;
 
        ActorMovieId that = (ActorMovieId) o;
        return Objects.equals(actorId, that.actorId) &&
               Objects.equals(movieId, that.movieId);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(actorId, movieId);
    }

}
