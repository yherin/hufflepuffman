package com.js.huffman.model.structures.node;

/**
 * Represents a Node that been 'reconstructed' from Metadata in an encoded
 * binary file.
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

    /**
     * Return string representation.
     * @return string rep.
     */
    @Override
    public final String toString() {
        if (this.hasLeft() && this.hasRight()) {
            return "(" + this.symbol + ", " + "L:" + this.left.toString() + "R:" + this.right.toString();
        } else {
            return "(" + this.symbol + "}";
        }
    }

}
