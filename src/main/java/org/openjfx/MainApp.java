package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import serial.SerialCommunication;

import javax.swing.*;
import java.sql.SQLOutput;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SerialCommunication serialCommunication = new SerialCommunication("COM3");
        try {
            serialCommunication.start();
            while(true) {
                System.out.println(serialCommunication.receiveMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            serialCommunication.close();
        }

        launch(args);
    }

}