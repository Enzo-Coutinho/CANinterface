package org.openjfx;

import CANBus.CANFrameDecode;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import serial.SerialCommunication;

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
                byte[] canMessage = serialCommunication.receiveMessage();
                CANFrameDecode canFrameDecode = new CANFrameDecode(canMessage);
                System.out.println("=============================");
                System.out.println("Full CAN Frame: " + canFrameDecode.getFullCANFrame());
                System.out.println("Header CAN: " + Integer.toUnsignedString(canFrameDecode.getHeader(), 16));
                System.out.println("Flags: " + Integer.toUnsignedString(canFrameDecode.getFlags(), 16));
                System.out.println("Data: " +  Long.toUnsignedString(canFrameDecode.getData(), 16));
                System.out.println("Device Type: " + canFrameDecode.getDeviceName());
                System.out.println("Manufacturer Type: " + canFrameDecode.getManufacturerName());
                System.out.println("API Class: " + Integer.toUnsignedString(canFrameDecode.getApiClass(), 16));
                System.out.println("Index Class: " + Integer.toUnsignedString(canFrameDecode.getApiIndex(), 16));
                System.out.println("Device number: " + Integer.toUnsignedString(canFrameDecode.getDeviceNumber(), 16));
                if(canFrameDecode.isRoboRioHeartbeat()) {
                    System.out.println("RoboRIO Heartbeat");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            serialCommunication.close();
        }

        //launch(args);
    }

}