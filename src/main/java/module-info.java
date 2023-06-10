module HuffdecompFX {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens org.decompression to javafx.fxml;
    opens org.drawtree to javafx.fxml;

    exports org.decompression to javafx.graphics;
    exports org.drawtree to javafx.graphics;
    exports org.menuButton to javafx.graphics;
    opens org.menuButton to javafx.fxml;
}