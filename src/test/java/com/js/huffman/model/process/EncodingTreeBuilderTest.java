/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import com.js.huffman.model.structures.node.heap.NodeHeap;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class EncodingTreeBuilderTest {

    private final NodePriorityComparator comp;
    private final NodeHeap nodes;
    private final HuffmanTree x;

    public EncodingTreeBuilderTest() {

        comp = new NodePriorityComparator();
        nodes = new NodeHeap(comp, 4);
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

//    @After
//    public void tearDown() {
//        nodes.clear();
//    }
    @Test
    public void rootFrequencyEqualsTotalFrequency() {

        assertTrue(x.getRoot().getFreq() == 120);
    }

    @Test
    public void symbolsAreEncodedCorrectly() {
        String expectedSymbolRep = "bcad";
        String actualSymbolRep = x.getTreeSymbolsString();
        assertEquals(expectedSymbolRep, actualSymbolRep);
    }

    @Test
    public void treeRepIsEncodedCorrectlyInBytes() {
        NodeKey[] expectedRep = new NodeKey[]{NodeKey.ONE, NodeKey.ZERO, NodeKey.ONE, NodeKey.ZERO, NodeKey.ONE, NodeKey.ONE, NodeKey.FAKE, NodeKey.FAKE};
        byte a = x.getTreeByteRep()[0];
        int fake = x.getEmptyBitsTreeByteRep();
        NodeKey[] actualRep = BitUtils.decodeBits(a, fake);
        assertTrue(expectedRep.length == actualRep.length);
        for (int i = 0; i < expectedRep.length; i++) {
            assertEquals(expectedRep[i], actualRep[i]);
        }
    }
}
