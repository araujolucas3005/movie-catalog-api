package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.Actor;
import com.moviecatalog.repository.custom.CustomActorRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer>, CustomActorRepository {

}
