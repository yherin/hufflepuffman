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
 * @author jack
 */
public class BitInputStream extends FileInputStream{
    
    static final Logger logger = Logger.getLogger(BitOutputStream.class.getName());
    final long inputSize;
    long totalBytes;
    long readBytes;
    
    /**
     * Creates a new BitInputStream, suitable for reading bits
     * @param file file to be read.
     * @param emptyBits expected 'dead/empty' bits at EOF.
     * @throws FileNotFoundException 
     */
    public BitInputStream(File file, int emptyBits) throws FileNotFoundException {
        super(file);
        inputSize = file.length();
    }
    
//    public Character decodeBits(){
//        try {
//            final byte readByte = (byte) super.read();
//            NodeKey[] bitRepresentation = decodeBits(readByte);
//        } catch (IOException ex) {
//            Logger.getLogger(BitInputStream.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    /**
     * Evaluate the bits in the given byte, returning an array representing a 
     * binary string version of the given byte.
     * @param readByte
     * @return 
     */
    private NodeKey[] decodeBits(byte readByte) {
        NodeKey[] bits = new NodeKey[8];
        for (int i = 0; i < 8; i++) {
            int result = (readByte & (byte) 0b1 << i);
            if (result != 0){
                bits[i] = NodeKey.ONE;
            } else if (result == 0){
                bits[i] = NodeKey.ZERO;
            } else {
                logger.log(Level.SEVERE,"Non binary digit interpreted from byte.");
                throw new NumberFormatException("Result should have been a binary digit, result was "+result);
            }
        }
        readBytes++;
        return bits;
    }
    
}
