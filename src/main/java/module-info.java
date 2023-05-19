module HuffdecompFX {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports org.decompression to javafx.graphics;
    exports org.drawtree to javafx.graphics;
}