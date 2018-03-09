package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.NodeKey;

/**
 * Provides methods for bit manipulation.
 * @author jack
 */
public class BitUtils {


    /**
     * Evaluate the bits in the given byte, returning an array representing a
     * binary string version of the given byte.
     *
     * @param readByte the byte to be read.
     * @param fakeBits the number of bits to ignore (starting from 0)
     * @return NodeKey[] an array of @NodeKey representing the byte.
     */
    public static NodeKey[] decodeBits(byte readByte, int fakeBits) {
        NodeKey[] bits = new NodeKey[8];
        for (int i = 7; i >= 0; i--) {
            if (i < fakeBits) {
                /* if param fakeBits is not 0, we will write some 'fake/empty'
                *  into this byte.
                */
                bits[bits.length - 1 - i] = NodeKey.FAKE;
            } else {
                /**
                 * If true, bit is 1 at index i
                 */
                boolean isOne = (readByte & ((byte) 1 << i)) != 0;
                if (isOne) {
                    bits[bits.length - 1 - i] = NodeKey.ONE;
                } else {
                    bits[bits.length - 1 - i] = NodeKey.ZERO;
                }
            }
        }
        return bits;
    }

}
