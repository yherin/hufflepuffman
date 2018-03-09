package com.js.huffman.model.structures.node;

/**
 * Class represents a Node in a HuffmanTree that has been 'built' from a text file, 
 * that is, not reconstructed from binary.
 * @author jack
 */
public class BuiltNode extends Node implements Comparable<Node> {

    /**
     * Create a [leaf] Built Node, a Node created from original uncompressed data.
     * @param character
     * @param count
     */
    public BuiltNode(final Character character, final Integer count) {
        super(character, count);
        determineNodeType();
        assert (symbol != null);
    }

    /**
     * Create a [branch] Built Node, a Node created from original uncompressed data.
     *
     * @param l the node which will be the left child of the new node.
     * @param r the node which will be the right child of the new node.
     */
    public BuiltNode(final Node l, final Node r) {
        super(null, l.getFreq() + r.getFreq());

        this.left = l;
        this.right = r;
        determineNodeType();
        this.left.keyCode = NodeKey.ZERO;
        this.right.keyCode = NodeKey.ONE;

        this.left.determineNodeType();
        this.right.determineNodeType();
    }

    /**
     * toString
     * @return String representation of this Node.
     */
    @Override
    public final String toString() {
        if (this.symbol == null) {
            return "( NO SYMBOL, " + this.freq + ")";

        }
        return "(" + this.symbol + ", " + this.freq + ")";
    }

    /**
     * Not implemented, do not use.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Node o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
