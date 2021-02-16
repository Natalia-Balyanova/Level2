package ru.geekbrains.level2.lesson1.balyanova;

public class Treadmill implements Barrier {
    private int lenght;

    public Treadmill(int lenght) {
        this.lenght = lenght;
    }

    @Override
    public void check(Entity entity) {
        entity.run(lenght);
    }
}
