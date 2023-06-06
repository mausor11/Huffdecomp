package org.drawtree;

public class ArrayXY {
    private int level;
    private char sign;
    public ArrayXY(int level, char sign) {
        this.level = level;
        this.sign = sign;
    }
    public int getLevel() {
        return level;
    }
    public char getSign() {
        return sign;
    }
    public void add(int level, char sign) {
        this.level = level;
        this.sign = sign;
    }
}
