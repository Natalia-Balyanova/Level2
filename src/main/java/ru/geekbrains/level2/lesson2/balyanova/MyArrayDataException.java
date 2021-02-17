package ru.geekbrains.level2.lesson2.balyanova;

public class MyArrayDataException extends Exception {
    public int i;
    public int j;

    MyArrayDataException(int i, int j) {
        this.i = i;
        this.j = j;

    }
}