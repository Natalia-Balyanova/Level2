package ru.geekbrains.level2.lesson1.balyanova;

public class Robot implements Entity {
    private String name;
    private int maxLength;
    private int maxHeight;
    private boolean success = true;

    public Robot(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    @Override
    public String getName() {
        return name;
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
        System.out.println("Robot " + getName() + " run");
    }

    @Override
    public void jump() {
        System.out.println("Robot " + getName() + " jump");
    }
    @Override
    public int getMaxLength() {
        return maxLength;
    }
    @Override
    public int getMaxHeight() {
        return maxHeight;
    }
    public String finisher() {
        if (getSuccess()) {
            return getName();
        }
        return null;
    }
}
