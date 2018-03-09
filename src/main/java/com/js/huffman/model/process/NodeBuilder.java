package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;

/**
 * Provides functionality for create Node objects.
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
     *
     * @param c the character that the node represents.
     * @param i the frequency of the character
     * @return the created @Node.
     */
    public static Node buildLeafNode(final Character c, final Integer i) {
        Node n = new BuiltNode(c, i);
        return n;
    }

}
