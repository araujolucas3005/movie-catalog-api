package com.moviecatalog.custom.structures;

public interface StackInter<T> {
	
	public void push(T t);
	
	public T pop() throws Exception;
	
	public T peek() throws Exception;
	
	public boolean isEmpty();

}
