package com.codewithlfn;

public class RedBlackTree {
    RBNode root;

    // tree is leaning right so we tip it to the left
    void rotateLeft(RBNode node) {
        RBNode rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;

        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }

        rightChild.left = node;
        node.parent = rightChild;
    }

    // tree is leaning left so we tip it to the right
    void rotateRight(RBNode node) {
        RBNode leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;

        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.right) {
            node.parent.right = leftChild;
        } else {
            node.parent.left = leftChild;
        }

        leftChild.right = node;
        node.parent = leftChild;
    }

    // adds a new value into the tree
    void insert(int value) {
        RBNode newNode = new RBNode(value);

        // if tree is empty this becomes the root
        if (root == null) {
            root = newNode;
            newNode.colour = Colour.BLACK;
            return;
        }

        // walk down to find the right spot
        RBNode parentNode = null;
        RBNode current = root;
        while (current != null) {
            parentNode = current;
            if (newNode.value < current.value) {
                current = current.left;
            } else if (newNode.value > current.value) {
                current = current.right;
            } else {
                return; // already in the tree, skip it
            }
        }

        newNode.parent = parentNode;
        if (newNode.value < parentNode.value) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        // check and fix any colour violations
        fixColours(newNode);
    }

    // walks back up fixing any double-red violations
    private void fixColours(RBNode node) {
        while (node.parent != null && node.parent.colour == Colour.RED) {
            RBNode grandParent = node.parent.parent;

            // parent is on the left side
            if (node.parent == grandParent.left) {
                RBNode uncle = grandParent.right;

                // uncle is red so we just recolour
                if (uncle != null && uncle.colour == Colour.RED) {
                    node.parent.colour = Colour.BLACK;
                    uncle.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    node = grandParent;
                } else {
                    // uncle is black so we need to rotate
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    rotateRight(grandParent);
                }
            }
            // parent is on the right side
            else {
                RBNode uncle = grandParent.left;

                // uncle is red so we just recolour
                if (uncle != null && uncle.colour == Colour.RED) {
                    node.parent.colour = Colour.BLACK;
                    uncle.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    node = grandParent;
                } else {
                    // uncle is black so we need to rotate
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.colour = Colour.BLACK;
                    grandParent.colour = Colour.RED;
                    rotateLeft(grandParent);
                }
            }
        }
        root.colour = Colour.BLACK; // root must always stay black
    }

    // prints all values in sorted order to prove insertion worked
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(RBNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.value + " [" + node.colour + "]  ");
            printInOrder(node.right);
        }
    }
}