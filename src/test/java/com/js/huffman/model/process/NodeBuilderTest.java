/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class NodeBuilderTest {

    public NodeBuilderTest() {
    }

    @Test
    public void nodeBuilderReturnsValidNode() {
        Node a = NodeBuilder.buildLeafNode('@', 1);
        doAssertions(a, new BuiltNode('@', 1));
    }

    private void doAssertions(Node x, Node node) {
        assertNotNull(x);
        assertTrue(x instanceof Node);
        assertEquals(x, node);
    }
}
