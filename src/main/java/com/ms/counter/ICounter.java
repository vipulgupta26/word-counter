package com.ms.counter;

/**
    *  This is an interface
    *  K is generic type
    */
public interface ICounter<K> {
    public void add(K k) throws Exception;
    public int getOccurrences(K k) throws Exception;
}
