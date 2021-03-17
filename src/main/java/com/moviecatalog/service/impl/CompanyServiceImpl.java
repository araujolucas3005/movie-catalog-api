package com.moviecatalog.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.CompanyRepository;
import com.moviecatalog.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, CompanyRepository> implements CompanyService {
	
	@Autowired private CompanyRepository compRepo;

	public ResponseEntity<Set<Movie>> findAllMovies(Integer id) {
		Optional<Company> company = compRepo.findById(id);
		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(company.get().getMovies());
	}
	
}
