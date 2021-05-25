package com.moviecatalog.repository.custom;

import com.moviecatalog.custom.structures.LinkedListInter;

public interface BaseCustomRepository<T> {

	public LinkedListInter<T> findAllAsLinkedList();
	
}
