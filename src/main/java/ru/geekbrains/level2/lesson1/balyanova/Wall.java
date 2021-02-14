package ru.geekbrains.level2.lesson1.balyanova;

public class Wall implements Barrier {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public void check(Entity entity) {
        entity.jump();
        entity.setSuccess(entity.getMaxHeight() >= height);
        if (entity.getSuccess()) {
            System.out.println(entity.getName() + " climb " + height + " m");
        } else {
            System.out.println(entity.getName() + " doesn`t climb " + height + " m");
        }
    }
}
