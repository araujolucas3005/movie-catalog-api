package com.moviecatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moviecatalog.model.Company;
import com.moviecatalog.repository.custom.CustomCompanyRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>, CustomCompanyRepository {

}
