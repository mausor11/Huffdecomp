module HuffdecompFX {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;

    opens org.decompression to javafx.fxml;

    exports org.decompression to javafx.graphics;
    exports org.drawtree to javafx.graphics;
}