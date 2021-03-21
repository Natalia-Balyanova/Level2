package ru.geekbrains.balyanova.marchchat;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class ClientHandler { //обработчик клиентов

    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String username;
    private final Date date = new Date();

    public String getUsername() {
        return username;
    }

    public ClientHandler(Server server, Socket socket) throws IOException { //принимаем сокет
        this.server = server; //отдали ссылку на сервер
        this.socket = socket; //запоминаем его
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
        new Thread(() -> {
            try {
                // Цикл авторизации
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/login ")) {
                        String usernameFromLogin = msg.split("\\s")[1];
                        if (server.isNickBusy(usernameFromLogin)) {
                            sendMessage("/login_failed Current nickname is already used");
                            continue;
                        }

                        username = usernameFromLogin;
                        sendMessage("/login_ok " + username);
                        server.subscribe(this);
                        break;
                    }
                }
                // Цикл общения с клиентом
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/")) {
                        executeCommands(msg);
                        continue;
                    }
                    if (!msg.isEmpty() & !msg.startsWith(" ")) {
                        server.broadcastMessage("[ " + date + " ]\n" + username.toUpperCase(Locale.ROOT) + ": " + msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                    disconnect();
            }
        }).start();
    }

    private void executeCommands(String cmd) throws IOException {
        if(cmd.equals("/exit")) {
            server.broadcastMessage(username + " покинул чат");
            disconnect();
        }
       if(cmd.startsWith("/w ")) {
           String[] tokens = cmd.split("\\s", 3);
           server.sendPrivateMessage(this, tokens[1], tokens[2]);
           return;
       }
       if(cmd.startsWith("/change_nick ")) {
            myNewNickname(cmd);
       }
    }
    public void myNewNickname(String cmd) {//меняем ник
        String[] tokens = cmd.split("\\s",2);
        String newNickname = tokens[1];
        if (newNickname != null) {
            if (!server.isNickBusy(newNickname)) {
                server.broadcastMessage("Клиент " + username + " поменял имя на " + newNickname);
                username = newNickname;
                sendMessage("/change_nick_ok " + username);
                server.add(this);
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            disconnect();
        }
    }

    public void disconnect() {
        server.unsubscribe(this);//отписались от рассылки при закрытии
        if(socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
