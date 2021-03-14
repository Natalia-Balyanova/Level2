package ru.geekbrains.balyanova.marchchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClientHandler { //обработчик клиентов
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private Date date = new Date();

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
                int counter = 0;
                while (true) {
                    String msg = in.readUTF();
                    counter++;
                    if(msg.startsWith("/")) {
                        counter--;//не считаем служебные сообщения
                        if (msg.equals("/exit")) {
                            server.broadcastMessage(username + " покинул чат");
                            socket.close();//разрываем соединение
                        }
                        if (msg.equals("/stat")) {//считаем и показываем сообщения одного клиента
                            server.broadcastMessage("Total messages from " + username + ": " + counter);
                        }
                        if (msg.equals("/who_am_i")) {//отправляем ник
                            sendMessage("Ваше имя: " + username);
                        }
                        if (msg.startsWith("/@")) {//не получилось с "w" т.к. уже есть команда начинающаяся на "w"
                            String to = msg.split("\\s")[1];//берем адресата
                            String secretMsg = msg;
                            server.ghostMessage(this, to, secretMsg);
                            sendMessage(msg);//дублируем сообщение себе
                        }

                    }  else if (!msg.isEmpty() & !msg.startsWith(" ")) {
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

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
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
