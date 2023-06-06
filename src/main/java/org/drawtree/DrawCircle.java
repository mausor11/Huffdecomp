package org.drawtree;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DrawCircle {
    final Circle circle;
    final int size;
    final char sign;

    public DrawCircle(int size, char sign) {
        this.size = size;
        this.sign = sign;
        circle = new Circle();
        circle.setFill(Color.web("#5e10d9"));
        circle.setStrokeWidth(0);
        circle.setStroke(Color.web("#7B728B"));
        circle.setRadius(size);
    }
    private Text Letter() {
        Text text = new Text(String.valueOf(sign));
        text.setFont(Font.font("Arial", FontWeight.BOLD, size));
        text.setFill(Color.web("#EBE1FA"));
        text.setTranslateX(-text.getLayoutBounds().getWidth() / 2);
        text.setTranslateY(text.getLayoutBounds().getHeight() / 4);
        return text;
    }
    public static Group newCircle(int size, char sign) {
        DrawCircle drawCircle = new DrawCircle(size, sign);
        return new Group(drawCircle.circle, drawCircle.Letter());
    }

}
