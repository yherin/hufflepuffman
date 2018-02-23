/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class NodeTest {

    @Test
    public void testNodeType() {
        Node n = new BuiltNode('a', 10);
        n.determineNodeType();
        assertTrue(n.type == NodeType.LEAF);
        Node m = new ReconstructedNode(NodeKey.ONE);
        m.determineNodeType();
        assertTrue(m.type == NodeType.BRANCH);

    }

}
