package com.a2;

/**
 * @param <K> key
 * @param <V> value
 * This interface defines a map entry
 */
public interface MapEntry<K, V>{
    K getKey();
    V getValue();
    V setValue(V value);
    boolean equals(Object o);
    int hashCode();
}

