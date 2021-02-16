package ru.geekbrains.level2.lesson1.balyanova;

public class Wall implements Barrier {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void check(Entity entity) {
        entity.jump(height);
    }
}
