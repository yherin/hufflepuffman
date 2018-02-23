package com.js.huffman.model.structures.node;

import java.util.Objects;

/**
 * Abstract node class. Gives shared functionality to the two non-abstract
 * node classes.
 * @author jack
 */
public abstract class Node {

    protected final Integer freq;
    protected final Character symbol;

    protected boolean isRoot;
    /**
     * This node's left child.
     */
    protected Node left;
    /**
     * This node's right child.
     */
    protected Node right;

    /**
     * NodeType of this node.
     */
    protected NodeType type;

    /**
     * The binary representation of this node in the Huffman code.
     */
    protected NodeKey keyCode;

    public Node(Character symbol, Integer freq) {
        this.freq = freq;
        this.symbol = symbol;
    }

    @Override
    public final boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Node)) {
            return false;
        }
        Node n = (Node) o;
        return this.hashCode() == n.hashCode();
    }

    @Override
    public final int hashCode() {
        int hash = 5;
        if (this.freq != null) {
            hash = 67 * hash + Objects.hashCode(this.freq);
        }
        if (this.symbol != null) {
            hash = 67 * hash + Objects.hashCode(this.symbol);
        }
        return hash;
    }

    public final Integer getFreq() {
        return freq;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public Character getSymbol() {
        return symbol;
    }

    public NodeKey getKey() {
        return keyCode;
    }

    public boolean isLeaf() {
        return this.type == NodeType.LEAF;
    }

    public boolean isBranch() {
        return this.type == NodeType.BRANCH;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public void setRoot() {
        this.isRoot = true;
        assert this.type != NodeType.LEAF;
    }

    protected final void determineNodeType() {
        if (this.symbol == null) {
            this.type = NodeType.BRANCH;
        } else {
            this.type = NodeType.LEAF;
        }
    }

    public void setLeft(ReconstructedNode left) {
        this.left = left;
    }

    public void setRight(ReconstructedNode right) {
        this.right = right;
    }

    public final void setKeyCode(NodeKey keyCode) {
        if (keyCode != NodeKey.FAKE) {
            IllegalArgumentException e = new IllegalArgumentException("Not allowed to set non fake node keys.");
            throw e;
        }
        this.keyCode = keyCode;
    }

}
