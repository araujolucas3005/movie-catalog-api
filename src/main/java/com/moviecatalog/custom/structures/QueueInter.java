package com.moviecatalog.custom.structures;

public interface QueueInter <T>{
    boolean isEmpty() throws Exception;

    void enqueue(T elemento);

    T dequeue();

    T peek();

    int size();
}
