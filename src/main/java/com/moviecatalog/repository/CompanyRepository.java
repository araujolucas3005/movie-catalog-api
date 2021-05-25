package com.moviecatalog.repository;

import com.moviecatalog.model.Company;
import com.moviecatalog.repository.custom.CustomCompanyRepository;

public interface CompanyRepository extends BaseRepository<Company, Integer>, CustomCompanyRepository {

}
