package org.drawtree;

public class ArrayXY {
    private final int level;
    private final char sign;
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
}
