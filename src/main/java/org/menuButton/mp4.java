package org.menuButton;

import javafx.scene.control.MenuButton;

public class mp4 implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("mp4");
        return ".mp4";
    }
}
