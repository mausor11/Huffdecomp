package org.decompression;

import java.util.*;

public class Array2D{
    public Map<Integer, ArrayList<Character>> levels;

    public Array2D() {
        this.levels = new HashMap<>();
    }


    public void add(int lvl, char sign) {
        if(!levels.containsKey(lvl)) {
            levels.put(lvl, new ArrayList<Character>());
        }
        levels.get(lvl).add(sign);
    }
    public void print() {
        for(int level: levels.keySet()) {
            System.out.println("Level: " + level + ": ");
            ArrayList<Character> items = levels.get(level);
            for(char sign: items) {
                System.out.println("    " + sign);
            }
        }
        System.out.println(levels.get(5));
    }
}
