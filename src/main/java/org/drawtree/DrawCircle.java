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
    final int X;
    final int Y;
    final char sign;
    public DrawCircle(int size, int X, int Y, char sign, int n) {
        this.size = size;
        this.X  = X;
        this.Y = Y;
        this.sign = sign;
        circle = new Circle();
        if(n == 1) {
            circle.setFill(Color.web("#5e10d9"));
            circle.setStrokeWidth(20);
            circle.setStroke(Color.web("#7B728B"));
        } else {
            circle.setFill(Color.TRANSPARENT);
            circle.setStroke(Color.TRANSPARENT);
        }
        circle.setRadius(size);
        circle.setCenterX(X);
        circle.setCenterY(Y);
    }
    public static Group newCircle(int size, int X, int Y, char sign, int n) {
        DrawCircle drawCircle = new DrawCircle(size, X, Y, sign, n);
        return new Group(drawCircle.circle, drawCircle.newLetter());
    }
    private Text newLetter() {
        Text text = new Text(String.valueOf(sign));
        text.setFont(Font.font("Arial", FontWeight.BOLD, size));
        text.setFill(Color.web("#EBE1FA"));
        text.setX(X - text.getBoundsInLocal().getWidth() / 2);
        text.setY(Y + text.getBoundsInLocal().getHeight() / 4);
        return text;
    }
}
