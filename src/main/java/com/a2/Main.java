package com.a2;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GenHashMap<Integer, String> list = new GenHashMap<>();
        System.out.println(list.entrySet());
        list.put(1, "One");
        list.put(2, "Two");
        System.out.println(list.entrySet());

        list.put(2,"Two1");
        System.out.println(list.entrySet());
        list.clear();
        System.out.println(list.keyset());
    }
}