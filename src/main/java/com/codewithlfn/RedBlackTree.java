package com.codewithlfn;

public class RedBlackTree {
    RBNode root;

    void rotateLeft(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;

        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    void rotateRight(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != null) y.right.parent = x;
        y.parent = x.parent;

        if (x.parent == null) root = y;
        else if (x == x.parent.right) x.parent.right = y;
        else x.parent.left = y;

        y.right = x;
        x.parent = y;
    }

    void insert(int data) {
        RBNode newNode = new RBNode(data);

        // bst insertion
        if (root == null) {
            root = newNode;
            newNode.colour = Colour.BLACK;
            return;
        }

        RBNode parent = null;
        RBNode current = root;
        while (current != null) {
            parent = current;
            if (newNode.data < current.data){
                current = current.left;
            } else if (newNode.data > current.data) {
                current = current.right;
            } else {
                return; // No duplicates allowed
            }
        }

        newNode.parent = parent;
        if (newNode.data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        // fix the colour of the parent
        fixInsert(newNode);
    }

    private void fixInsert(RBNode newNode) {
        while (newNode.parent != null && newNode.parent.colour == Colour.RED) {
            RBNode grandParent = newNode.parent.parent;

            // the parent is the left child
            if (newNode.parent == grandParent.left) {
                RBNode uncle = grandParent.right;
                // uncle is red
                if (uncle != null && uncle.colour == Colour.RED) {
                    newNode.parent.colour = Colour.BLACK;
                    uncle.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    newNode = grandParent;
                } else {
                    // uncle is black or null
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        rotateLeft(newNode);
                    }

                    // uncle is black or null
                    newNode.parent.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    rotateRight(grandParent);
                }
            }
            // the parent is the right child
            else {
                RBNode uncle = grandParent.left;
                // uncle is red
                if (uncle != null && uncle.colour == Colour.RED) {
                    newNode.parent.colour = Colour.BLACK;
                    uncle.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    newNode = grandParent;
                } else {
                    // uncle is black or null
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rotateRight(newNode);
                    }

                    // uncle is black or null
                    newNode.parent.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    rotateLeft(grandParent);
                }
            }
        }
        root.colour = Colour.BLACK; // root is always black
    }

    // Demonstration
    public void printTree(RBNode node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "+-- " : "\\-- ") + node.data + " [" + node.colour + "]");
            printTree(node.left, prefix + (isLeft ? "|   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "|   " : "    "), false);
        }
    }
}
