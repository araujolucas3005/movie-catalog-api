package com.moviecatalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.moviecatalog.model.BaseModel;

public abstract class BaseServiceImpl<T extends BaseModel<T>, Repository extends JpaRepository<T, Integer>> {
	
	@Autowired private Repository repo;
	
	public ResponseEntity<T> findById(Integer id) {
		return repo.findById(id).map(entity -> ResponseEntity.ok(entity))
				.orElse(ResponseEntity.notFound().build());
	}
	
	public ResponseEntity<T> save(T entity) {
		return ResponseEntity.ok(repo.save(entity));
	}
	
	public ResponseEntity<T> update(Integer id, T source) {
		return repo.findById(id).map(entity -> {
			entity.update(source);
			return ResponseEntity.ok(repo.save(entity));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	public ResponseEntity<Void> delete(Integer id) {
		if (!repo.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		repo.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}	
