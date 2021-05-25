package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.moviecatalog.repository.custom.BaseCustomRepository;

@NoRepositoryBean
public interface BaseRepository<T, IdType> extends JpaRepository<T, IdType>, BaseCustomRepository<T> {

}
