package org.decompression;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
        @Override
    public void start(Stage stage) throws Exception {
        stage = StageSingleton.getStageInstance();
        Image appIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("ICON2.png")));
        stage.getIcons().add(appIcon);
        stage.setTitle("Huffdecomp");
        Scene scene = new Scene(new FXMLLoader(Main.class.getResource("menu.fxml")).load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}