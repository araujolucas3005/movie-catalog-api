package com.moviecatalog.custom.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DoublyLinkedListInter<T>{

    void pushFirst(T elemento);

    void pushLast(T elemento);

    void pushAfter(T elemento) ;

    T peekFirst() throws Exception;

    T peekLast() throws Exception;

    T deleteFirst() throws Exception;

    T deleteLast() throws Exception;

    T delete(T elemento) throws Exception;

    T searchPosition(int id);

    public Object formatToJSONObject() throws JsonMappingException, JsonProcessingException;

}
