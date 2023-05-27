package org.decompression;

import java.util.ArrayList;

public class Points {
    public final ArrayList<Point> coordinate;

    public Points() {
        coordinate = new ArrayList<>();
    }
    public static class Point {
        public final int X;
        public final int Y;
        public final int index;
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




}
