//package com.moviecatalog.custom.models;
//
//import com.moviecatalog.custom.structures.LinkedListInter;
//import com.moviecatalog.custom.structures.impl.SimplyLinkedList;
//import com.moviecatalog.model.Genre;
//
//public class CustomGenre {
//	
//	private Integer id;
//
//	private String name;
//
//	private Genre parentGenre;
//
//	private LinkedListInter<Genre> subgenres;
//
//	private LinkedListInter<Integer> movies;
//	
//	public CustomGenre(Genre genre) {
//		this.id = genre.getId();
//		this.name = genre.getName();
//		this.parentGenre = genre.getParentGenre();
//		
//		movies = new SimplyLinkedList<>();
//		genre.getMovies().forEach(movie -> movies.add(movie.getId()));
//		
//		subgenres = new SimplyLinkedList<>();
//		genre.getSubgenres().forEach(subgenre -> subgenres.add(subgenre));
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
//	public Genre getParentGenre() {
//		return parentGenre;
//	}
//
//	public void setParentGenre(Genre parentGenre) {
//		this.parentGenre = parentGenre;
//	}
//
//	public LinkedListInter<Genre> getSubgenres() {
//		return subgenres;
//	}
//
//	public void setSubgenres(LinkedListInter<Genre> subgenres) {
//		this.subgenres = subgenres;
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
