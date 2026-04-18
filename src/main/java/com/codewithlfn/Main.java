package com.codewithlfn;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        System.out.println("Inserting 10, 20, 30, 15, 25 into Red-Black Tree...\n");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // Triggers Left Rotation
        tree.insert(15); // Triggers Recoloring
        tree.insert(25); // Triggers Recoloring

        System.out.println("Red-Black Tree Structure:");
        tree.printTree(tree.root, "", false);
    }
}