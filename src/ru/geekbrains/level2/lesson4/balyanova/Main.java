package ru.geekbrains.level2.lesson4.balyanova;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.setStyle("-fx-background-color: CADETBLUE");// цвет рамки
        primaryStage.getIcons().add(new Image("/Images/png.smile.png"));//добавила иконку
        primaryStage.setTitle(" Ф  Л  У  Д  И  Л  К  А");
        primaryStage.setResizable(false);//нельзя менять размер окна
        primaryStage.setScene(new Scene(root, 460, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
