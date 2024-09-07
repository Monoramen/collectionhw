package com.a2;


import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        GenHashMap<String, String> map = new GenHashMap<>(16);
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        System.out.println(map.entrySet());
    }
}