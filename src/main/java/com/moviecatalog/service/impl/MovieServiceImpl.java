package com.moviecatalog.service.impl;

import org.springframework.stereotype.Service;

import com.moviecatalog.model.Movie;
import com.moviecatalog.repository.MovieRepository;
import com.moviecatalog.service.MovieService;

@Service
public class MovieServiceImpl extends BaseServiceImpl<Movie, MovieRepository> implements MovieService {

}
