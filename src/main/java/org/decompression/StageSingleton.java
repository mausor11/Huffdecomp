package org.decompression;

import javafx.application.Application;
import javafx.stage.Stage;

public class StageSingleton extends Application {
    private static Stage stageInstance;
    public static Stage getStageInstance() {
        if(stageInstance == null) {
            stageInstance = new Stage();
        }
        return stageInstance;
    }
    @Override
    public void start(Stage stage) {
        throw new UnsupportedOperationException("Singleton Stage");
    }
}
