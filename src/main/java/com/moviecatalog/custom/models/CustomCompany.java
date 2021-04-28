//package com.moviecatalog.custom.models;
//
//import com.moviecatalog.custom.structures.LinkedListInter;
//import com.moviecatalog.custom.structures.impl.SimplyLinkedList;
//import com.moviecatalog.model.Company;
//
//public class CustomCompany {
//	
//	private Integer id;
//
//	private String name;
//
//	private LinkedListInter<Integer> movies;
//	
//	public CustomCompany(Company company) {
//		this.id = company.getId();
//		this.name = company.getName();
//		
//		this.movies = new SimplyLinkedList<>();
//		company.getMovies().forEach(movie -> movies.add(movie.getId()));
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public LinkedListInter<Integer> getMovies() {
//		return movies;
//	}
//
//	public void setMovies(LinkedListInter<Integer> movies) {
//		this.movies = movies;
//	}
//
//}
