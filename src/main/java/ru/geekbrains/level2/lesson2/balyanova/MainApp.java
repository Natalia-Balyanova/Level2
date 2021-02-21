package ru.geekbrains.level2.lesson2.balyanova;

public class MainApp {
    public static void main(String[] args) {
        String[][] arr = new String[][]{{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        try {
            try {
                int sum = methodArray(arr);
                System.out.println(sum);
            } catch (MyArraySizeException e) {
                e.printStackTrace();
                System.out.println("Array is not [4][4] format");
            }
        } catch (MyArrayDataException e) {
            e.printStackTrace();
            System.out.println("Wrong value in the cell\nFind error in the cell: [" + e.i + "][" + e.j + "]");
        }
    }

    public static int methodArray(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (arr.length != 4) {
            throw new MyArraySizeException();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 4) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < arr[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
//                    System.out.println("Wrong format of value");
                    throw new MyArrayDataException(i, j);
                }
            }
        } return sum;
    }
}

/*
Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4.
При подаче массива другого размера необходимо бросить исключение MyArraySizeException.

Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать.
Если в каком-то элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа),
должно быть брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.

В методе main() вызвать полученный метод, обработать возможные исключения MyArraySizeException и MyArrayDataException
и вывести результат расчета (сумму элементов, при условии что подали на вход корректный массив).

Заметка: Для преобразования строки к числу используйте статический метод класса Integer:
Integer.parseInt(сюда_подать_строку);
Заметка: Если Java не сможет преобразовать входную строку (в строке число криво написано),
то будет сгенерировано исключение NumberFormatException.

 */