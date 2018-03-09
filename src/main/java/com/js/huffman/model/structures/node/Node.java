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

    /**
     * Create a [leaf] Node.
     * @param symbol
     * @param freq
     */
    public Node(Character symbol, Integer freq) {
        this.freq = freq;
        this.symbol = symbol;
    }

    /**
     * Equals method for Node comparison.
     * @param o
     * @return true if equality by hashCode is true, else false.
     */
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

    /**
     * Generates hashCode based on Node frequency and symbol.
     * @return this object's hashCode.
     */
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

    /**
     * This node's frequency.
     * @return frequency
     */
    public final Integer getFreq() {
        return freq;
    }

    /**
     * This node's left child.
     * @return left child.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * This node's right child.
     * @return right child.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Check if Node has left child.
     * @return true if Node has left child, else false.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Check if Node has right child.
     * @return true if Node has right child, else false.
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Return this Node's symbol.
     * @return this node's symbol.
     */
    public Character getSymbol() {
        return symbol;
    }

    /**
     * Return this Node's nodekey
     * @return nodekey
     */
    public NodeKey getKey() {
        return keyCode;
    }

    /**
     * Check if this node is a leaf.
     * @return true iff this Node is a leaf.
     */
    public boolean isLeaf() {
        return this.type == NodeType.LEAF;
    }

    /**
     * Check if this node is a branch.
     * @return true iff this Node is a branch.
     */
    public boolean isBranch() {
        return this.type == NodeType.BRANCH;
    }

    /**
     * Set this Node's NodeType.
     * @param type
     */
    public void setType(NodeType type) {
        this.type = type;
    }

    /**
     * Check if this Node is the root node.
     * @return true iff this Node is root.
     */
    public boolean isRoot() {
        return this.isRoot;
    }

    /**
     * Set this node as root.
     */
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

    /**
     * Set this Node's left child.
     * @param left
     */
    public void setLeft(ReconstructedNode left) {
        this.left = left;
    }

    /**
     * Set this Node's right child.
     * @param right
     */
    public void setRight(ReconstructedNode right) {
        this.right = right;
    }

    /**
     * Set this Node's key code. Throws exception if keyCode is NodeKey.FAKE.
     * @param keyCode
     * @throw IllegalArgumentException
     */
    public final void setKeyCode(NodeKey keyCode) {
        if (keyCode != NodeKey.FAKE) {
            IllegalArgumentException e = new IllegalArgumentException("Not allowed to set non fake node keys.");
            throw e;
        }
        this.keyCode = keyCode;
    }

}
