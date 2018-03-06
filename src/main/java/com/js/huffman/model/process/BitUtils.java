package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.NodeKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides methods for bit manipulation.
 * @author jack
 */
public class BitUtils {

    private static final Logger LOG = Logger.getLogger(BitUtils.class.getName());

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
        if (fakeBits != 0) {
            //LOG.log(Level.WARNING, "Discarding {0} fake bits. This message should only appear once.", fakeBits);
        }
        for (int i = 7; i >= 0; i--) {
            if (i < fakeBits) {
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
        //0 1 2 3 4 5 6 7
        //
        return bits;
    }

}
