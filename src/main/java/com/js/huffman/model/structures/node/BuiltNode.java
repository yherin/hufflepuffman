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
public class BuiltNode extends Node implements Comparable<Node> {

  
    public BuiltNode(final Character character, final Integer count) {
        super(character, count);
        determineNodeType();
        assert (symbol != null);
    }

    /**
     * Creates a Node representing a branch node in the tree.
     *
     * @param l the node which will be the left child of the new node.
     * @param r the node which will be the right child of the new node.
     */
    public BuiltNode(final Node l, final Node r) {
        super(null, l.getFreq()+r.getFreq());
        

        this.left = l;
        this.right = r;
        determineNodeType();
        this.left.keyCode = NodeKey.ZERO;
        this.right.keyCode = NodeKey.ONE;

        this.left.determineNodeType();
        this.right.determineNodeType();
    }

    @Override
    public final String toString() {
        if (this.symbol == null){
                    return "( NO SYMBOL, " + this.freq + ")";

        }
        return "(" + this.symbol + ", " + this.freq + ")";
    }


    @Override
    public int compareTo(Node o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
