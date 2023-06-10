package org.menuButton;

import javafx.scene.control.MenuButton;

public class mp3 implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("mp3");
        return ".mp3";
    }
}
