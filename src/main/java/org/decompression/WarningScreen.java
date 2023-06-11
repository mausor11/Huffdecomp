package org.decompression;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javafx.scene.shape.*;


public class WarningScreen extends Application {
    private String text;
    public WarningScreen(String text) {
        this.text = text;
    }
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #0C021B");
        root.setSpacing(40);
        GaussianBlur gaussianBlur = new GaussianBlur(20);

        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(608);
        rectangle.setHeight(400);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.web("#5e10d9"));
        rectangle.setStrokeWidth(40);
        rectangle.setEffect(gaussianBlur);

        Rectangle background = new Rectangle();
        background.setWidth(608);
        background.setHeight(400);
        background.setFill(Color.web("#0C021B"));
        background.setStroke(Color.TRANSPARENT);
        background.setStrokeWidth(40);

        VBox textArea = new VBox();
        textArea.setAlignment(Pos.CENTER);
        textArea.setPrefWidth(608);
        textArea.setPrefHeight(400);
        Label warningText = new Label();
        warningText.setPadding(new Insets(20,20,20,20));
        warningText.setTextFill(Color.web("#5e10d9"));
        warningText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        warningText.setText("Warning!");
        textArea.getChildren().add(warningText);
        Label warningMessage = new Label();
        warningMessage.setPadding(new Insets(30,30,30,30));
        warningMessage.setTextFill(Color.web("#5e10d9"));
        warningMessage.setStyle("-fx-text-alignment: center");
        warningMessage.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        warningMessage.setText(this.text);
        warningMessage.setWrapText(true);
        textArea.getChildren().add(warningMessage);
        Group group = new Group();
        group.getChildren().addAll(rectangle,background, textArea);
        root.getChildren().add(group);
        Button goBack = new Button();
        goBack.setText("Go back");
        goBack.setTextFill(Color.web("#5e10d9"));
        goBack.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        goBack.setPrefHeight(60);
        goBack.setPrefWidth(200);
        goBack.getStyleClass().add("goBack");

        goBack.setOnAction(event -> {
            Main main = new Main();
            try {
                main.start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        root.getChildren().add(goBack);
        Scene scene = new Scene(root, 1216, 800);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();


    }

    //public static void main(String[] args) {
     //   launch();
    //}
}
