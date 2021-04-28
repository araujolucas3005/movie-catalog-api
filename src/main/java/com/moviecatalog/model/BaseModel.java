package com.moviecatalog.model;

public interface BaseModel<T> {
	
	public Integer getId();
	
	public void update(T source);
	
	public String toJSONString();

}
