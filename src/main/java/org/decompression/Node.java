package org.decompression;

import java.util.*;

public class Node{
    public int index;
    public byte sign;
    int counter;
    public Node left;
    public Node right;
    final int k;
    int level;
    public int number;


    public Node() {
        this.sign = 0;
        this.counter = 0;
        this.left = null;
        this.right = null;
        this.k = 0;
    }
    private void writeTree(Node node) {
        writeNode(node, 0);
//        levels.print();
    }
    private void writeNode(Node root,  int i) {
        if(root != null) {
            i++;
            writeNode(root.left, i);
            System.out.println("====\tlevel " + i + "| ascii num:" + (char)root.sign + "; ascii char: " + (int)root.sign + "; count: " + root.counter + "; index: " + root.index);
            writeNode(root.right, i);
        }
    }
    private static int writeNodeAndLevel(Node root, int number, int level) {
        if(root != null) {
            number = writeNodeAndLevel(root.left, number, level +1);
            root.number = number++;
            root.level = level;
            number = writeNodeAndLevel(root.right, number, level +1);
        }
        return number;
    }
    private static void printNodeAndLevel(Node root) {
        if(root != null){
            printNodeAndLevel(root.left);
            System.out.println("Node: " + root.sign + ";Number: " + root.number + "; Level: " + root.level);
            printNodeAndLevel(root.right);
        }
    }
    private Node printLevelOrder(Node root) {
        int index = 0;
        if(root == null) {
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(true) {
            int nodeCount = q.size();
            if(nodeCount == 0) {
                break;
            }

            while(nodeCount > 0) {
                Node node = q.peek();
                if(node.left == null) {
                    System.out.print("[-]" + index + " ");
                }
                if (node.right == null) {
                    System.out.print("[-]" + index + " ");
                }
                node.index = index;
                System.out.print("[" + (char)node.sign + "]" + index + " ");
                q.remove();
                if(node.left != null) {
                    q.add(node.left);
                }
                if(node.right != null) {
                    q.add(node.right);
                }
                nodeCount--;
                index++;
            }
            System.out.println();

        }
        writeTree(root);
        System.out.println(q);
        return root;
    }
    public static boolean hasLeftChildren(Node root) {
        return root != null && root.left != null;
    }
    public static boolean hasRightChildren(Node root) {
        return root != null && root.right != null;
    }


}
