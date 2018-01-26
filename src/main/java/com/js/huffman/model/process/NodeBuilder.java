/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.Node;

/**
 *
 * @author jack
 */
public class NodeBuilder {

    /**
     * Private constructor for utility class.
     */
    private NodeBuilder() {
    }

    /**
     * Builds and returns a Node.
     * @param c the character that the node represents.
     * @param i the frequency of the character
     * @return the created @Node.
     */
    public static Node buildLeafNode(final Character c, final Integer i) {
        Node n = new Node(c, i);
        return n;
    }

}
