package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
