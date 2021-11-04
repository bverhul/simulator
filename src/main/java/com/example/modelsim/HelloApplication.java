package com.example.modelsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Simulator");
        stage.setScene(scene);
        stage.show();
        HelloController helloController = (HelloController) fxmlLoader.getController();
        helloController.init();
    }

    public static void main(String[] args) {
        launch();
    }
}