package org.decompression;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception {
        Image appIcon = new Image(getClass().getResourceAsStream("ICON2.png"));
        stage.getIcons().add(appIcon);
        Menu menu = new Menu();
        menu.start(stage);
    }
}