package ru.geekbrains.balyanova.marchchat.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static int counter;//счетчик
    String name = "User: name";
    @FXML
    TextArea chatArea;

    @FXML
    TextField msgField;
    Date date = new Date();//добавила дату

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            Thread t = new Thread(() -> {
                try {
                    while (true) {
                        String msg = in.readUTF();
                        chatArea.appendText(msg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t.start();

        } catch (IOException e) {
            throw new RuntimeException("Unable to connect to server [ localhost:8189 ]");
        }
    }

    public void reply() {
        try {
            String msg = msgField.getText() + "\n";
            if(msgField.getText().length() > 140){
                Alert bigger = new Alert(AlertType.WARNING, "Cлишком длинное сообщение", ButtonType.CLOSE);
                bigger.showAndWait();
                msgField.wait();//оставляю текст, чтобы клиент его сократил
            }

            if (!msgField.getText().isEmpty()) {//не даю отправить пустое сообщение
                out.writeUTF("[ " + date + " ] \n" + name + ": " + msgField.getText() + "\n");
                counter++;
            }

            if (msgField.getText().equals("/stat")) {
                out.writeUTF("total messages: " + counter + "\n");//показываем кол-во сообщений
            }
            if (msgField.getText().equals("/exit")) {
                Alert exit = new Alert(AlertType.WARNING, "Вы покинули чат! Связь с сервером потеряна", ButtonType.CLOSE);
                exit.showAndWait();
                socket.close();//разрываем соединение
            }
            msgField.clear();

        } catch (IOException | InterruptedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Нет связи с сервером! Невозможно отправить сообщение", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void sendSmile() {
        try {
            out.writeUTF("[ " + date + " ] \n" + name + ": " + msgField.getText() + "=D =D =D \n");
            counter++;
            msgField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile() {
        try {
            out.writeUTF("[ " + date + " ] \n" + name + ": " + msgField.getText() + "отправил файл \n");
            counter++;
            msgField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

