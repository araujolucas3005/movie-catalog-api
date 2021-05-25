package com.moviecatalog.controller;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.moviecatalog.model.Company;
import com.moviecatalog.service.CompanyService;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService compServ;

	@GetMapping("/companies")
	public Object index(
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String order)
			throws JsonMappingException, JsonProcessingException, AttributeNotFoundException {
		return compServ.findAllAndSort(sortBy, order);
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<Company> show(@PathVariable Integer id) {
		return compServ.findById(id);
	}

	@GetMapping("/company/{id}/movies")
	public ResponseEntity<Object> findAllMovies(@PathVariable Integer id)
			throws JsonMappingException, JsonProcessingException {
		return compServ.findAllMovies(id);
	}

	@PostMapping("/company")
	public ResponseEntity<Company> save(@RequestBody Company company) {
		return compServ.save(company);
	}

	@PutMapping("/company/{id}")
	public ResponseEntity<Company> update(@PathVariable Integer id, @RequestBody Company company) {
		return compServ.update(id, company);
	}

	@DeleteMapping("/company/{id}")
	public ResponseEntity<Void> destroy(@PathVariable Integer id) {
		return compServ.delete(id);
	}

}
