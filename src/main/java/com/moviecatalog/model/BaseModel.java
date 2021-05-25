package com.moviecatalog.model;

import javax.management.AttributeNotFoundException;

public interface BaseModel<T> {
	
	public Integer getId();
	
	public void update(T source);
	
	public String toJSONString();
	
	public Integer compareTo(T other, String attribute) throws AttributeNotFoundException;

}
