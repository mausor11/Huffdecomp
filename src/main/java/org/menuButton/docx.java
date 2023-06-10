package org.menuButton;

import javafx.scene.control.MenuButton;

public class docx implements ButtonStatus {
    @Override
    public String changeExtension(MenuButton menuButton) {
        menuButton.setText("docx");
        return ".docx";
    }
}