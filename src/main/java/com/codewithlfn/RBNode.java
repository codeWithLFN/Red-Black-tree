package com.codewithlfn;

public class RBNode {
    int data;
    Colour colour;
    RBNode left, right, parent;

    // Constructor
    public RBNode(int data) {
        this.data = data;
        this.colour = Colour.RED;
    }
}
