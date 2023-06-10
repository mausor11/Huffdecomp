package org.drawtree;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Map;

public class DrawLine {
    final Line line;
    final int startX;
    final int startY;
    final int endX;
    final int endY;

    private DrawLine(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.web("#7B728B"));
        line.setStrokeWidth(20);
    }

}
