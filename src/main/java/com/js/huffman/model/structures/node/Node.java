/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node;

import java.util.Objects;

/**
 *
 * @author jack
 */
public class Node implements Comparable<Node> {

    private final Integer freq;
    private final Character symbol;
    private boolean isRoot;
    /**
     * This node's parent node.
     */
    private Node parent;
    /**
     * This node's left child.
     */
    private Node left;
    /**
     * This node's right child.
     */
    private Node right;

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
    public Node(final Character character, final Integer count) {
        this.freq = count;
        this.symbol = character;
        determineNodeType();
        assert (symbol != null);
    }

    /**
     * Creates a Node representing a branch node in the tree.
     *
     * @param l the node which will be the left child of the new node.
     * @param r the node which will be the right child of the new node.
     */
    public Node(final Node l, final Node r) {
        this.freq = l.freq + r.freq;
        this.symbol = null;
        

        this.left = l;
        this.right = r;
        determineNodeType();
        this.left.keyCode = NodeKey.ZERO;
        this.right.keyCode = NodeKey.ONE;

        this.left.determineNodeType();
        this.right.determineNodeType();
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

        if (n.freq == null) {
            return false;
        }

        return this.hashCode() == n.hashCode();

    }

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.freq);
        hash = 67 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public final String toString() {
        return "(" + this.symbol + ", " + this.freq + ")";
    }

    public final Integer getFreq() {
        return freq;
    }

    @Override
    public int compareTo(Node o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
    
    public boolean hasLeft(){
        return left != null;
    }
    
    public boolean hasRight(){
        return right != null;
    }

    public Character getSymbol() {
        return symbol;
    }

    public NodeKey getKey() {
        return keyCode;
    }
 
    public boolean isLeaf(){
        return this.type == NodeType.LEAF;
    }
    
    public boolean isBranch(){
        return this.type == NodeType.BRANCH;
    }

    public void setType(NodeType type) {
        this.type = type;
    }
    
    private void determineNodeType(){
        if (this.symbol==null){
            this.type=NodeType.BRANCH;
        } else {
            this.type=NodeType.LEAF;
        }
    }
    
    public boolean isRoot(){
        return this.isRoot;
    }
    
    public void setRoot(){
        this.isRoot = true;
        assert this.type != NodeType.LEAF;
    }
    
}
