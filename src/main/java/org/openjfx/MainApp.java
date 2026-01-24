package org.openjfx;

import CANBus.CANMessageFormatter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import serial.SerialCommunication;

import javax.swing.*;

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
        SerialCommunication serialCommunication = new SerialCommunication("COM6");
        try {
            serialCommunication.start();
            while(true) {
                byte[] canMessage = serialCommunication.receiveMessage();
                CANMessageFormatter canMessageFormatter = new CANMessageFormatter(canMessage);
                System.out.println("Full CAN Frame: " + canMessageFormatter.getFullCANFrame());
                System.out.println("Header CAN: " + Integer.toUnsignedString(canMessageFormatter.getHeader(), 16));
                System.out.println("Flags: " + Integer.toUnsignedString(canMessageFormatter.getFlags(), 16));
                System.out.println("Data: " +  Long.toUnsignedString(canMessageFormatter.getData(), 16));
            }
        } catch (Exception e) {
            e.printStackTrace();
            serialCommunication.close();
        }

        //launch(args);
    }

}