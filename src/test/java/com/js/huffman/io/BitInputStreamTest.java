/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.model.structures.node.NodeKey;
import java.io.File;
import java.io.FileNotFoundException;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class BitInputStreamTest {

    private final BitInputStream stream;

    public BitInputStreamTest() throws FileNotFoundException {
        /**
         * Our test file: total symbols:4 {a=00, b=11, c=10, d=01} Feb 09, 2018
         * 7:01:12 PM com.js.huffman.model.count.HuffmanEncoder encodeBits INFO:
         * Total 10 bytes written. Each symbol 10 times. So the sequence of bit
         * symbols must be 00111001
         *
         */
        stream = new BitInputStream(new File("src/test/plain/binary_abcd"));
        stream.setEmptyBits(0);
    }

    public void testNodeKeySequence() {
        NodeKey[] first = stream.readByte();
        assertTrue(first[0] == NodeKey.ZERO);
        assertTrue(first[1] == NodeKey.ZERO);
        assertTrue(first[2] == NodeKey.ONE);
        assertTrue(first[3] == NodeKey.ONE);
        assertTrue(first[4] == NodeKey.ONE);
        assertTrue(first[5] == NodeKey.ZERO);
        assertTrue(first[6] == NodeKey.ZERO);
        assertTrue(first[7] == NodeKey.ONE);
    }

}
