package org.decompression;

import javafx.application.Application;
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
    public Menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Huffdecomp =====");
        System.out.print("Enter input file: ");
        inputFile = scanner.nextLine();
        System.out.print("Enter output file file: ");
        outputFile = scanner.nextLine();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tree");

        Label inputFile = new Label("INPUT");
        inputFile.setAlignment(Pos.CENTER);
        inputFile.setFont(Font.font(30));
        Label outputFile = new Label("OUTPUT");
        outputFile.setAlignment(Pos.CENTER);
        outputFile.setFont(Font.font(30));
        TextField t1 = new TextField();
        t1.setMinHeight(70);
        t1.setMinWidth(200);
        t1.setFont(Font.font(30));
        TextField t2 = new TextField();
        t2.setMinHeight(70);
        t2.setFont(Font.font(30));
        Button button = new Button("Decompression");
        button.setFont(Font.font(30));
        GridPane info = new GridPane();
        info.addRow(0, inputFile, t1);
        info.addRow(1, outputFile, t2);
        info.setHgap(20);
        info.setVgap(10);
        info.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.getChildren().addAll(info, button);
        root.setAlignment(Pos.CENTER);


        button.setOnAction(event -> {
            this.inputFile = t1.getText();
            this.outputFile = t2.getText();
            return;
        });

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    String inputFile;
    String outputFile;
    String password;
    public void passwordRequired() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your file is encrypted");
        System.out.println("Password required. Please enter the password.");
        System.out.print("Password: ");
        this.password = scanner.nextLine();
    }
}
