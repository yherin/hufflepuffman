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
public class ReconstructedNode {

    private Character symbol;
    private boolean isRoot;
    /**
     * This node's parent node.
     */
    private ReconstructedNode parent;
    /**
     * This node's left child.
     */
    private ReconstructedNode left;
    /**
     * This node's right child.
     */
    private ReconstructedNode right;

    /**
     * NodeType of this node.
     */
    private NodeType type;

    /**
     * The binary representation of this node in the Huffman code.
     */
    private NodeKey keyCode;

    /**
     * Creates a Node representing a leaf node in the tree.
     *
     * @param character the character represented by this node.
     * @param count the frequency of this symbol.
     */
    public ReconstructedNode(final Character character) {
        this.symbol = character;
        assert (symbol != null);
    }

    /**
     * Creates a Node representing a branch node in the tree.
     *
     * @param keycode
     */
   public ReconstructedNode(final NodeKey keycode){
       this.keyCode = keycode;
   }

    @Override
    public final boolean equals(final Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != Node.class) {
            return false;
        }

        Node n = (Node) o;

        return this.hashCode() == n.hashCode();

    }

    @Override
    public final String toString() {
        if (this.hasLeft() && this.hasRight()) {
            return "(" + this.symbol + ", " + "L:" + this.left.toString() + "R:" + this.right.toString();
        } else {
            return "(" + this.symbol + "}";
        }
    }

    public ReconstructedNode getLeft() {
        return left;
    }

    public ReconstructedNode getRight() {
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

    private void determineNodeType() {
        if (this.symbol == null) {
            this.type = NodeType.BRANCH;
        } else {
            this.type = NodeType.LEAF;
        }
    }

    public boolean isRoot() {
        return this.isRoot;
    }

    public void setRoot() {
        this.isRoot = true;
        assert this.type != NodeType.LEAF;
    }

    public void setParent(ReconstructedNode parent) {
        this.parent = parent;
    }

    public void setLeft(ReconstructedNode left) {
        this.left = left;
    }

    public void setRight(ReconstructedNode right) {
        this.right = right;
    }
    
    
}
