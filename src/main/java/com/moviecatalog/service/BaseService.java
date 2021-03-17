package com.moviecatalog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BaseService<T> {
	
	public List<T> findAll();
	
	public ResponseEntity<T> findById(Integer id);
	
	public ResponseEntity<Void> delete(Integer id);
	
	public ResponseEntity<T> save(T entity);
	
	public ResponseEntity<T> update(Integer id, T entity);

}
