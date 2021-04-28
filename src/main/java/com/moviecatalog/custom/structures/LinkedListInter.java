package com.moviecatalog.custom.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface LinkedListInter<T> {
	
	public void add(T t);
	
	public T peekFirst() throws Exception;
	
	public T peekLast() throws Exception;
	
	public T removeFirst() throws Exception;
	
	public T removeLast() throws Exception;
	
	public Object formatToJSONObject() throws JsonMappingException, JsonProcessingException;

}
