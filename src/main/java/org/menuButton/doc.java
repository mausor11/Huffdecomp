package org.menuButton;

import javafx.scene.control.MenuButton;

public class doc implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("doc");
        return ".doc";
    }
}
