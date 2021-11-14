package com.example.modelsim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    public static String[] args;
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
            stage.setTitle("Simulator");
            stage.setScene(scene);
            stage.show();
            HelloController helloController = (HelloController) fxmlLoader.getController();
            helloController.init(HelloApplication.args);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        HelloApplication.args = args;
        launch();
    }
}