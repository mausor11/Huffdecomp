package org.menuButton;

import javafx.scene.control.MenuButton;

public class png implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("png");
        return ".png";
    }
}
