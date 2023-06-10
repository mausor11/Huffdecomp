package org.menuButton;

import javafx.scene.control.MenuButton;

public class jpeg implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("jpeg");
        return ".jpeg";
    }
}
