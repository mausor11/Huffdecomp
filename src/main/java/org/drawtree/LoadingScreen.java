package org.drawtree;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class LoadingScreen extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Huffdecomp");
        AnchorPane root = new AnchorPane();
        root.getStyleClass().add("screen");
        LinearGradient linearGradient = new LinearGradient(0, 0, 0.5, 0.5, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#5e10d9")), new Stop(1, Color.web("transparent")));
        GaussianBlur gaussianBlur = new GaussianBlur(5);
        Circle circleLoading = new Circle();

        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), circleLoading);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setAutoReverse(false);
        rotateTransition.play();

        circleLoading.setRadius(30);
        circleLoading.setEffect(gaussianBlur);
        circleLoading.setFill(Color.TRANSPARENT);
        circleLoading.setStroke(linearGradient);
        circleLoading.setStrokeWidth(10);
        circleLoading.setLayoutX(608);
        circleLoading.setLayoutY(400);
        root.getChildren().add(circleLoading);
        Scene scene = new Scene(root, 1216, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());


        stage.setScene(scene);
        stage.show();
    }

}
