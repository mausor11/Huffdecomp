package org.decompression;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Menu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //elo
        stage.setTitle("Huffdecomp");
        Scene scene = new Scene(new FXMLLoader(Menu.class.getResource("menu.fxml")).load());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
