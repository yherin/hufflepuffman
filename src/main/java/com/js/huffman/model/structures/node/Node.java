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
    
    private Integer freq;
    private Character symbol;
    
    private Node parent;
    private Node left;
    private Node right;
    
    private NodeType type;
    private NodeKey key;
    
    
    public Node(final Character symbol, final Integer freq){
        this.freq = freq;
        this.symbol = symbol;
        this.type = NodeType.LEAF;
        
    }
    
    public Node(final Node a, final Node b){
        this.freq = a.freq + b.freq;
        this.symbol = null;
        this.type = NodeType.BRANCH;

        
        this.left = a;
        this.right = b;
        
        this.left.key = NodeKey.ZERO;
        this.right.key = NodeKey.ONE;
        
        this.left.type = NodeType.LEAF;
        this.right.type = NodeType.LEAF;
        
        
        
    }
 
    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        
        if (o.getClass() != Node.class){
            return false;
        }
        
        Node n = (Node) o;
        
        if (n.freq == null){
            return false;
        }
        
        return this.hashCode() == n.hashCode();
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.freq);
        hash = 67 * hash + Objects.hashCode(this.symbol);
        return hash;
    }


    
    @Override
    public String toString(){
        return "("+this.symbol+", "+this.freq+")";
    }

    public Integer getFreq() {
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
        return key;
    }
 
    public boolean isLeaf(){
        return this.type == NodeType.LEAF;
    }
    
    public boolean isBranch(){
        return this.type == NodeType.BRANCH;
    }
    
}
