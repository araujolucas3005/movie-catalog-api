package com.moviecatalog.repository;

import com.moviecatalog.model.Actor;
import com.moviecatalog.repository.custom.CustomActorRepository;

public interface ActorRepository extends BaseRepository<Actor, Integer>, CustomActorRepository {

}
