package org.menuButton;

import javafx.scene.control.MenuButton;

public class gif implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("gif");
        return ".gif";
    }
}
