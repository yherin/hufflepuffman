/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node;

/**
 *
 * @author jack
 */
public class ReconstructedNode extends Node {

    /**
     * Creates a Node representing a leaf node in the tree.
     *
     * @param symbol the character represented by this node.
     * @param count the frequency of this symbol.
     */
    public ReconstructedNode(final Character symbol) {
        super(symbol, null);
        assert (symbol != null);
        super.determineNodeType();
    }

    /**
     * Creates a Node representing a branch node in the tree.
     *
     * @param keycode
     */
    public ReconstructedNode(final NodeKey keycode) {
        super(null, null);
        this.keyCode = keycode;
        super.determineNodeType();
    }

    @Override
    public final String toString() {
        if (this.hasLeft() && this.hasRight()) {
            return "(" + this.symbol + ", " + "L:" + this.left.toString() + "R:" + this.right.toString();
        } else {
            return "(" + this.symbol + "}";
        }
    }

}
