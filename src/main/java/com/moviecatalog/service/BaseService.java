package com.moviecatalog.service;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface BaseService<T> {
	
	public ResponseEntity<T> findById(Integer id);
	
	public ResponseEntity<Void> delete(Integer id);
	
	public ResponseEntity<T> save(T entity);
	
	public ResponseEntity<T> update(Integer id, T entity);
	
	public Object findAllAndSort(String attribute) throws JsonMappingException, JsonProcessingException, AttributeNotFoundException;

}
