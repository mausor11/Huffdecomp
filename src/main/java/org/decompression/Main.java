package org.decompression;
import javafx.application.Application;
import javafx.stage.Stage;
import org.drawtree.PrintTree;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch();

    }


    @Override
    public void start(Stage stage) throws Exception {
        Menu menu = new Menu();
        menu.start(stage);
//        Decompression decompression = new Decompression();
//        decompression.decode();
//        PrintTree printTree = new PrintTree(decompression.tree.root);
//        printTree.start(stage);
    }
}