package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.ActorMovie;
import com.moviecatalog.model.ActorMovieId;

public interface ActorMovieRepository extends JpaRepository<ActorMovie, ActorMovieId>{

}
