package com.moviecatalog.service.impl;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.BaseModel;
import com.moviecatalog.repository.BaseRepository;
import com.moviecatalog.service.BaseService;

public abstract class BaseServiceImpl<T extends BaseModel<T>, Repository extends BaseRepository<T, Integer>>
		implements BaseService<T> {

	@Autowired
	private Repository repo;

	public ResponseEntity<T> findById(Integer id) {
		return repo.findById(id).map(entity -> ResponseEntity.ok(entity)).orElse(ResponseEntity.notFound().build());
	}

	@Override
	public Object findAllAndSort(String attribute, String order)
			throws JsonMappingException, JsonProcessingException, AttributeNotFoundException {
		LinkedListInter<T> entities = repo.findAllAsLinkedList();
		
		entities.sort(attribute, order);
		
		return entities.formatToJSONObject();
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
