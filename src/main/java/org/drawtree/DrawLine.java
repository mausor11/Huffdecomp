package org.drawtree;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.decompression.Points;

import java.util.Map;

public class DrawLine {
    Line line;
    int startX;
    int startY;
    int endX;
    int endY;
    public DrawLine(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.line = new Line();
        line.setStartX(startX);
        line.setStartY(startY);
        line.setEndX(endX);
        line.setEndY(endY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
    }
    public static Line newLine(int startX, int startY, int endX, int endY) {
        DrawLine drawLine = new DrawLine(startX, startY, endX, endY);
        return drawLine.line;
    }
    public static void treeNet(Pane root, Points points, Map<Integer, Character> signs) {
        int outsideVar = 0;
        int insideVar = 0;
        int startX, startY, endX, endY;
        while(insideVar < points.coordinate.size() - 1) {
            startX = points.coordinate.get(outsideVar).X;
            startY = points.coordinate.get(outsideVar).Y;
            for (int i = 0; i < 2; i++) {
                insideVar++;
                endX = points.coordinate.get(insideVar).X;
                endY = points.coordinate.get(insideVar).Y;
                if(signs.get(insideVar) != null) {
                    root.getChildren().add(DrawLine.newLine(startX, startY, endX, endY));
                }
            }
            outsideVar++;
        }
    }
}
