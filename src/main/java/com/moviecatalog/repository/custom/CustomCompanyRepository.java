package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.LinkedListInter;
import com.moviecatalog.model.Company;
import com.moviecatalog.model.Movie;

public interface CustomCompanyRepository extends BaseCustomRepository<Company> {
	
	public LinkedListInter<Movie> findAllMovies(Integer id);

}
