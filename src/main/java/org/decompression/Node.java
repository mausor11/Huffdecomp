package org.decompression;

import java.util.LinkedList;
import java.util.Queue;

public class Node{
    public int index;
    public byte sign;
    int counter;
    public Node left;
    public Node right;
    int k;
    Array2D levels;
    public Node() {
        this.sign = 0;
        this.counter = 0;
        this.left = null;
        this.right = null;
        this.k = 0;
        this.levels = new Array2D();
    }
    public void writeTree(Node node) {
        writeNode(node, 0);
//        levels.print();
    }
    public void writeNode(Node root,  int i) {
        if(root != null) {
            i++;
            levels.add(i, (char) root.sign);
            System.out.println("====\tlevel " + i + "| ascii num:" + (char)root.sign + "; ascii char: " + (int)root.sign + "; count: " + root.counter + "; index: " + root.index);
            writeNode(root.left, i);
            writeNode(root.right, i);
        }
    }
    public Node printLevelOrder(Node root) {
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
        System.out.println(q.toString());
        return root;
    }


}
