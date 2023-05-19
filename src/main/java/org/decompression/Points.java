package org.decompression;

import java.util.ArrayList;

public class Points {
    public ArrayList<Point> coordinate;

    public Points() {
        coordinate = new ArrayList<>();
    }
    public static class Point {
        public int X;
        public int Y;
        public int index;
        public char sign;
        public Point(int X, int Y, int index, char sign) {
            this.X = X;
            this.Y = Y;
            this.index = index;
            this.sign = sign;
        }
    }
    public void addPoint(int X, int Y, int index, char sign) {
        Point point = new Point(X,Y,index, sign);
        coordinate.add(point);
    }
    public void printPoints() {
        for (int i = 0; i < coordinate.size(); i++) {
            System.out.println(coordinate.get(i).X + " " + coordinate.get(i).Y + " " + coordinate.get(i).sign + " " + coordinate.get(i).index);
        }
    }



}
