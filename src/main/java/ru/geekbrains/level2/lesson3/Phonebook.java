package ru.geekbrains.balyanova.level2.lesson3;

import java.util.*;

public class Phonebook {
    private HashMap<String,ArrayList<Long>> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<>();
    }

    public void add(String name, Long number) {
        ArrayList<Long> phoneList = phonebook.get(name);
        if (phoneList == null) phoneList = new ArrayList<>();
        phoneList.add(number);
        phonebook.put(name, phoneList);
    }

    public ArrayList<Long> get(String name) {
        System.out.println(name);
//        System.out.println(phonebook.get(name));
        return phonebook.get(name);
    }
}