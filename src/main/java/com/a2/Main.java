package com.a2;


import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        GenHashMap<Integer, String> list = new GenHashMap<>(0);

        list.put(0, "Zero");
        list.put(7, "Seven");
        list.put(2, "Two");
        list.put(6, "Six");
        list.put(3, "Three");
        list.put(4, "Four");
        list.put(5, "Five");
        list.put(1, "One");
        list.put(19,"Add New Value");
        System.out.println(list.entrySet());

        list.put(0, "Zero");
        list.setValue(0, "New Value");

        list.setValue(10, "Zero");

        System.out.println(list.entrySet());


    }
}