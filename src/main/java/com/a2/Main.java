package com.a2;


public class Main {
    public static void main(String[] args) {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        System.out.println(list.entrySet());
        list.put(1, "One");
        list.put(2, "Two");
        System.out.println(list.entrySet());
        list.put(2,"Two1");
        list.put(3, "Three");
        list.put(4, "Four");
        list.put(5, "Five");
    }
}