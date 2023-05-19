package org.drawtree;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DrawCircle {
    Circle circle;
    int size;
    int X;
    int Y;
    char sign;
    public DrawCircle(int size, int X, int Y, char sign, int n) {
        this.size = size;
        this.X  = X;
        this.Y = Y;
        this.sign = sign;
        circle = new Circle();
        if(n == 1) {
            circle.setFill(Color.LIGHTBLUE);
            circle.setStrokeWidth(1);
            circle.setStroke(Color.BLACK);
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
        text.setFill(Color.BLACK);
        text.setX(X - text.getBoundsInLocal().getWidth() / 2);
        text.setY(Y + text.getBoundsInLocal().getHeight() / 4);
        return text;
    }
}
