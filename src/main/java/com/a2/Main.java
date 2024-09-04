package com.a2;


public class Main {
    public static void main(String[] args) {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        System.out.println(list.entrySet());
        list.put(1, "One");
        list.put(2, "Two");
        list.put(3, "Three");
        list.put(4, "Four");
        list.put(5, "Five");
        list.put(6, "Six");
        list.put(7, "Seven");
        System.out.println(list.entrySet());
        list.clear();
        System.out.println(list.keyset());
    }
}