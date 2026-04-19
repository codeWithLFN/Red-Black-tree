package com.codewithlfn;

// each node stores a value, its colour, and links to its neighbours
public class RBNode {
    int value;
    Colour colour;
    RBNode left, right, parent;

    public RBNode(int value) {
        this.value = value;
        this.colour = Colour.RED; // new nodes always start as red
    }
}