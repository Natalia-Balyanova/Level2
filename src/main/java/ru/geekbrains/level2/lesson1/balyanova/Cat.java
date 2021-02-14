package ru.geekbrains.level2.lesson1.balyanova;

public class Cat implements Entity {
    private String name;
    private boolean success = true;
    private int maxLength;
    private int maxHeight;

    public Cat(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }

    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public void run() {
        System.out.println("Cat " + getName() + " run");
    }

    @Override
    public void jump() {
        System.out.println("Cat " + getName() + " jump");
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getMaxLength() {
        return maxLength;
    }
    @Override
    public int getMaxHeight() {
        return maxHeight;
    }
}