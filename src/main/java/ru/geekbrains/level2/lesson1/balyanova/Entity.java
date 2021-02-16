package ru.geekbrains.level2.lesson1.balyanova;

public interface Entity {
    String getName();
    boolean getSuccess();
    int getMaxLength();
    int getMaxHeight();

    void run(int length);
    void jump(int height);

    void setSuccess(boolean success);
}
