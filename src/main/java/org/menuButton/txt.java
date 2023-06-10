package org.menuButton;

import javafx.scene.control.MenuButton;

public class txt implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("txt");
        return ".txt";
    }
}
