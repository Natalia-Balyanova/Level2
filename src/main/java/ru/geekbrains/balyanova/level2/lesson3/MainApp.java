package ru.geekbrains.balyanova.level2.lesson3;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        List<String> fruit = new ArrayList<>();
        fruit.add("watermelon");
        fruit.add("apple");
        fruit.add("watermelon");
        fruit.add("banana");
        fruit.add("orange");
        fruit.add("apple");
        System.out.println(fruit);

        Set<String> unique = new HashSet<>(fruit);
        System.out.println(unique);
        for (String key : unique)
            System.out.println(key.toUpperCase() + " " + Collections.frequency(fruit, key));
        //короткое решение сортировка по возрастанию

        frequency(fruit);


        Phonebook phonebook = new Phonebook();
        phonebook.add("Ivanov", 89991234567L);
        phonebook.add("Petrov", 89112155707L);
        phonebook.add("Sokolov", 89923210829L);
        phonebook.add("Sokolov", 89067546445L);
        phonebook.add("Smirnov", 89186532458L);
        phonebook.add("Petrov", 89112116677L);

//        phonebook.get("Sokolov"); // можно использовать любой из вариантов вызова метода
        System.out.println(phonebook.get("Petrov"));

    }

    public static void frequency(List<String> array){ //свой метод подсчета
        //сортировка по порядку вложения элементов
        Set<String> temp = new LinkedHashSet<>(array); //или temp.addAll(array)
        for(String tempArray : temp) {
            int count = 0;
            for(String arr : array){
                if(tempArray.equals(arr)) count++;
            }
            System.out.println(tempArray.toUpperCase() + " " + count);
        }
        System.out.println();//отступ
    }
}
/*

Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
 Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
 Посчитать, сколько раз встречается каждое слово.

Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае
однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не добавлять
лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем через консоль
и т.д). Консоль использовать только для вывода результатов проверки телефонного справочника.
 */