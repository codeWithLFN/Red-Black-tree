package com.codewithlfn;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        // lets insert some student marks and watch the tree balance itself
        System.out.println("--- inserting marks into the Red-Black Tree ---\n");
        tree.insert(10);
        tree.insert(20);
        tree.insert(30); // this one triggers a left rotation
        tree.insert(15); // this one triggers recolouring
        tree.insert(25); // this one triggers recolouring

        // print all values with their colours to prove the rules are working
        System.out.print("values in order with colours: ");
        tree.printInOrder();
        System.out.println();
    }
}