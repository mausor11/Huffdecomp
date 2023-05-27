package org.decompression;

import java.util.*;

public class Array2D{
    public final Map<Integer, ArrayList<Character>> levels;

    public Array2D() {
        this.levels = new HashMap<>();
    }


    public void add(int lvl, char sign) {
        if(!levels.containsKey(lvl)) {
            levels.put(lvl, new ArrayList<>());
        }
        levels.get(lvl).add(sign);
    }
}
