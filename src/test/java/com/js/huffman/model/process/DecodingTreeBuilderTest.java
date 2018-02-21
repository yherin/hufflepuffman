/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.io.Metadata;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.io.UnsupportedEncodingException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class DecodingTreeBuilderTest {

    Metadata md1;
    Metadata md2;
    Metadata md3;
    HuffmanTree x;

    public DecodingTreeBuilderTest() throws UnsupportedEncodingException {
        byte[] symbolBytes = "adcb".getBytes("UTF8");
        byte[] treeRep = new byte[]{(byte) 0b01101100};
        md1 = new Metadata(15, symbolBytes, (byte) 2, (byte) 0, treeRep);

    }

    @Before
    public void setUp() {
        DecodingTreeBuilder dtb = new DecodingTreeBuilder(md1);
        x = dtb.buildTree();
    }

    @After
    public void tearDown() {
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
