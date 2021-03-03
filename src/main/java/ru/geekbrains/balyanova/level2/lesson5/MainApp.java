package ru.geekbrains.balyanova.level2.lesson5;

import java.util.Arrays;

public class MainApp {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
//    float[] arr = new float[SIZE];

    private static float[] fillArray() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);//заполняем массив "1"
        return arr;
    }

    public static void method1(float[] arr) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
            //Arrays.toString(arr);
            System.currentTimeMillis();//Засекаем время выполнения
            System.out.println();
            System.out.printf("Method running time: %d",(System.currentTimeMillis() - a));//вывод времени работы метода
    }

    public static void method2(float[] arr) {
        long a = System.currentTimeMillis();
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];
        System.arraycopy(arr, 0, a1, 0, HALF);//делим массив на 2
        System.arraycopy(arr, HALF, a2, 0, HALF);
        //Arrays.toString(a1);//выводим массив в строку
        //Arrays.toString(a2);
        Thread t1 = new Thread(() -> {//создали поток //лямбда с переопределенным методом run в классе MyRunnableClass
            for (int i = 0; i < a1.length; i++)
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < a2.length; i++)
                a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        });

        t1.start();//запустили потоки
        t2.start();
        try{
            t1.join();//ждем завершения потока
            //Thread.sleep(100);//остановка потока//не нужна
            t2.join();
        } catch (InterruptedException e) {//сгенерировано исключение
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF);//склейка
        System.arraycopy(a2, 0, arr, HALF, HALF);
        //Arrays.toString(arr);
        System.currentTimeMillis();
        System.out.printf("Method2 running time: %d",(System.currentTimeMillis() - a));
    }

    public static void main(String[] args) {
        method1(fillArray());
        System.out.println();//перенос строки
        method2(fillArray());
    }
}
/*
Для первого метода надо считать время только на цикл расчета
Для второго метода замеряете время разбивки массива на 2,
просчета каждого из двух массивов и склейки.
 */