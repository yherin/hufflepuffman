/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.process.BitUtils;
import com.js.huffman.model.process.EncodingTreeBuilder;
import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class HuffmanTreeTest {

    PriorityQueue<Node> nodes;
    NodePriorityComparator comp;
    HuffmanTree x;

    public HuffmanTreeTest() {
        comp = new NodePriorityComparator();
        nodes = new PriorityQueue<>(comp);
        nodes.add(new BuiltNode('a', 30));
        nodes.add(new BuiltNode('b', 30));
        nodes.add(new BuiltNode('c', 30));
        nodes.add(new BuiltNode('d', 30));
        EncodingTreeBuilder etb = new EncodingTreeBuilder(nodes);
        x = etb.buildTree();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        nodes.clear();
    }

    @Test
    public void rootFrequencyEqualsTotalFrequency() {

        assertTrue(x.getRoot().getFreq() == 120);
    }

    @Test
    public void symbolsAreEncodedCorrectly() {
        String expectedSymbolRep = "adcb";
        String actualSymbolRep = x.getTreeSymbolsString();
        assertEquals(expectedSymbolRep, actualSymbolRep);
    }

    @Test
    public void treeRepIsEncodedCorrectlyInBytes() {
        NodeKey[] expectedRep = new NodeKey[]{NodeKey.ZERO, NodeKey.ONE, NodeKey.ONE, NodeKey.ZERO, NodeKey.ONE, NodeKey.ONE, NodeKey.FAKE, NodeKey.FAKE};
        byte a = x.getTreeByteRep()[0];
        int fake = x.getEmptyBitsTreeByteRep();
        NodeKey[] actualRep = BitUtils.decodeBits(a, fake);
        assertTrue(expectedRep.length == actualRep.length);
        for (int i = 0; i < expectedRep.length; i++) {
            assertEquals(expectedRep[i], actualRep[i]);
        }
    }
}
