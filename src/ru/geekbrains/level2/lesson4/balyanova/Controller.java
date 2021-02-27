package ru.geekbrains.level2.lesson4.balyanova;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Date;

public class Controller {
    String name = "User: name";
    @FXML
    TextArea chatArea;

    @FXML
    TextField msgField;
    Date date = new Date();//добавила дату


    public void reply() {
        if (!msgField.getText().isEmpty()) {//не даю отправить пустое сообщение
            chatArea.appendText("[" + name + " " + date + "]\n" + msgField.getText() + "\n");
            msgField.clear();
        }
    }

    public void sendSmile() {
        chatArea.appendText("[" + name + " " + date + "]\n" + msgField.getText() + "=D =D =D \n");
        msgField.clear();
    }

    public void sendFile() {
        chatArea.appendText("[" + name + " " + date + "]\n" + msgField.getText() + " отправил файл \n");
        msgField.clear();
    }
}

