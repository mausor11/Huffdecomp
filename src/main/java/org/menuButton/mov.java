package org.menuButton;

import javafx.scene.control.MenuButton;

public class mov implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("mov");
        return ".mov";
    }
}
