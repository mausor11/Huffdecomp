package org.decompression;

public class Node{
    public int index;
    public byte sign;
    int counter;
    public Node left;
    public Node right;
    final int k;
    public int number;
    public Node() {
        this.sign = 0;
        this.counter = 0;
        this.left = null;
        this.right = null;
        this.k = 0;
    }
    public void writeTree(Node node) {
        writeNode(node, 0);
    }
    public void writeNode(Node root,  int i) {
        if(root != null) {
            i++;
            writeNode(root.left, i);
            System.out.println("====\tlevel " + i + "| ascii num:" + (char)root.sign + "; ascii char: " + (int)root.sign + "; count: " + root.counter + "; index: " + root.index);
            writeNode(root.right, i);
        }
    }
    public static boolean hasLeftChildren(Node root) {
        return root != null && root.left != null;
    }
    public static boolean hasRightChildren(Node root) {
        return root != null && root.right != null;
    }


}
