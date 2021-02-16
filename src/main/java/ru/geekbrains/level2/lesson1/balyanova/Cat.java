package ru.geekbrains.level2.lesson1.balyanova;

public class Cat implements Entity {
    private String name;
    private boolean success;
    private int maxLength;
    private int maxHeight;

    public Cat(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
        this.success = true;
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
    public void run(int length) {

        if (length <= maxLength) {
            System.out.println("Cat " + name + " run succesfully");
        } else {
            System.out.println(name + " doesn`t run");
            success = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxHeight) {
            System.out.println("Cat " + name + " jump succesfully");
        } else {
            System.out.println("Cat " + name + " doesn`t jump");
            success = false;
        }
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