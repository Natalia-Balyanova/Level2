package ru.geekbrains.balyanova.level2.lesson5;

public class MyRunnableClass implements Runnable{
    float[] arr;
    public MyRunnableClass(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

    }
}
