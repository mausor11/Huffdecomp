package org.decompression;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Scanner;
import java.util.Stack;

public class Menu extends Application {
    final int WIDTH = 1216;
    final int HEIGHT = 800;
//    public Menu() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("===== Huffdecomp =====");
//        System.out.print("Enter input file: ");
//        inputFile = scanner.nextLine();
//        System.out.print("Enter output file: ");
//        outputFile = scanner.nextLine();
//    }
    private static Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tree");
        scene = new Scene(new FXMLLoader(Menu.class.getResource("menu.fxml")).load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    String password;
    public void passwordRequired() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Your file is encrypted");
//        System.out.println("Password required. Please enter the password.");
//        System.out.print("Password: ");
//        this.password = scanner.nextLine();
        
    }

}
