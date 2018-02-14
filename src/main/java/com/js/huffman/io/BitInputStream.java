/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.model.structures.node.NodeKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for writing individual bits to a file.
 *
 * @author jack
 */
public class BitInputStream extends FileInputStream {

    static final Logger logger = Logger.getLogger(BitInputStream.class.getName());
    final long totalBytes;
    long remainingBytes;
    long readBytes;
    private int emptyBits;

    /**
     * Creates a new BitInputStream, suitable for reading bits
     *
     * @param file file to be read.
     * @throws FileNotFoundException
     */
    public BitInputStream(File file) throws FileNotFoundException {
        super(file);
        this.totalBytes = file.length();
        this.readBytes = 0l;
        this.remainingBytes = this.totalBytes;

    }

    public NodeKey[] readByte() {
        try {

            byte x = (byte) super.read();
            if (x == -1) {
                logger.log(Level.INFO, "Input stream returned -1. EOF.");
                return null;
            } else {

                readBytes++;
                remainingBytes--;
                String msg = "" + remainingBytes + " bytes left.";
            //    logger.log(Level.INFO, msg);
                if (remainingBytes == 0) {
                    logger.log(Level.INFO, "Trying to handle " + emptyBits + " empty bits.");
                    return decodeBits((byte) x, this.emptyBits);
                } else {
                    return decodeBits((byte) x, 0);
                }

            }

        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        logger.log(Level.SEVERE, "readByte returning null at end of function. Something probably went wrong.");
        return null;
    }

    /**
     * Evaluate the bits in the given byte, returning an array representing a
     * binary string version of the given byte.
     *
     * @param readByte
     * @return
     */
    private NodeKey[] decodeBits(byte readByte, int fakeBits) {
        NodeKey[] bits = new NodeKey[8];
        if (fakeBits != 0) {
            logger.log(Level.WARNING, "Discarding " + fakeBits + " fake bits. This message should only appear once.");
        }
        for (int i = 7; i >= 0; i--) {
            /**
             * If true, bit is 1
             */
            if (i< fakeBits) {
                bits[bits.length - 1 -i] = NodeKey.FAKE;
            } else {
                boolean isOne = (readByte & ((byte) 0b1 << i)) != 0;
                if (isOne) {
                    bits[bits.length - 1 - i] = NodeKey.ONE;
                } else {
                    bits[bits.length - 1 - i] = NodeKey.ZERO;
                }
            }
        }
        //0 1 2 3 4 5 6 7
        //              

        readBytes++;
        return bits;
    }

    public void setEmptyBits(int emptyBits) {
        this.emptyBits = emptyBits;
    }

}
