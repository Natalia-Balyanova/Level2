package ru.geekbrains.level2.lesson1.balyanova;

public class Human implements Entity{
    private String name;
    private boolean success = true;
    private int maxLength;
    private int maxHeight;

    public Human(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    public void run(int length) {

        if (length <= maxLength) {
            System.out.println("Human " + name + " run succesfully");
        } else {
            System.out.println("Human" + name + " doesn`t run");
            success = false;
        }
    }

    @Override
    public void jump(int height) {
        if(height <= maxHeight) {
            System.out.println("Human " + name + " jump succesfully");
        } else {
            System.out.println("Human " + name + " doesn`t jump");
            success = false;
        }
    }
    @Override
    public int getMaxLength() {
        return maxLength;
    }
    @Override
    public int getMaxHeight() {
        return maxHeight;
    }
    @Override
    public boolean getSuccess() {
        return success;
    }
    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

}
