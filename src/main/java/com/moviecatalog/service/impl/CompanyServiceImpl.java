package com.moviecatalog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.CompanyRepository;
import com.moviecatalog.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, CompanyRepository> implements CompanyService {
	
	@Autowired private CompanyRepository compRepo;

	public ResponseEntity<Object> findAllMovies(Integer id) throws JsonMappingException, JsonProcessingException {
		Optional<Company> company = compRepo.findById(id);
		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LinkedListInter<Movie> movies = compRepo.findAllMovies(id);
		return ResponseEntity.ok(movies.formatToJSONObject());
	}
	
}
