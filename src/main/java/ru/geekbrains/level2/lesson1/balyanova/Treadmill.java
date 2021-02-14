package ru.geekbrains.level2.lesson1.balyanova;

public class Treadmill implements Barrier {
    private int lenght;

    public Treadmill(int lenght) {
        this.lenght = lenght;
    }

    public void check(Entity entity) {
        entity.run();
        entity.setSuccess(entity.getMaxLength() >= lenght);
        if (entity.getSuccess()) {
            System.out.println(entity.getName() + " run distance " + lenght + " m"); }
        else {
            System.out.println(entity.getName() + " doesn`t run distance " + lenght + " m");

        }
    }
}
