/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class MetadataBuilder {

    private static final Logger LOG = Logger.getLogger(MetadataBuilder.class.getName());
    final static int MAX_METADATA_SIZE = 4096;
    
    /**
     * This method packs all the metadata that we will need to decode our file later
     * into a ByteBuffer.
     * Our metadata consists of the following structure:
     * - The FIRST 4 bytes of metadata are an @int, telling the total bytes of the metadata.
     * - The 5th byte contains the number of 'empty bits' in the final byte of
     * the tree representation
     * - The 6th byte contains the number of 'empty bits' in the final byte of this file
     * - The 7-11th bytes are an @int, telling the total length of the string (in bytes)
     * which represents the huffman tree.
     * -The remaining bytes are the symbols used by the huffman tree, where each symbol
     * is encoded in UTF-8 and therefore may occupy between 1 and 4 bytes.
     * 
     * The maximum size of the metadata is currently 4096 bytes.
     * @see Metadata
     * @param treeRep a byte array representation of the huffman tree
     * @param symbols a string containg the symbols used by the huffman tree, in an arbitrary order for later decoding.
     * @param fakeBitsTree the number of 'empty bits' in the final byte of @treeRep
     * @return ByteBuffer containing all data to be written.
     */
    public static ByteBuffer buildMetadataBuffer(final byte[] treeRep, final String symbols, final byte fakeBitsTree) {
        final byte fakeBitsEOF = 0; //WE DON'T YET KNOW THIS AMOUNT. PLACEHOLDER. IT IS WRITTEN AT THE END OF WRITING.
        final ByteBuffer MAX = ByteBuffer.allocate(MAX_METADATA_SIZE);
        int METADATA_SIZE = 0; //initial value
        byte[] symbolBytes = null;
        try {
            symbolBytes = symbols.getBytes("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(MetadataBuilder.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }

        assert symbolBytes != null;
        METADATA_SIZE = symbolBytes.length + treeRep.length + 4 + 1 + 1 + 4; //4 = size int, 1 is fakeBytesTree, 1 is fakeBytesEOF, 4 bytes for length of tree rep.
        putData(MAX, METADATA_SIZE, fakeBitsTree, fakeBitsEOF, treeRep, symbolBytes, treeRep.length);
        logInfo(METADATA_SIZE, treeRep, symbolBytes);
        MAX.flip();
        return MAX;
    }

    private static void putData(final ByteBuffer MAX, int METADATA_SIZE, final byte fakeBitsTree, final byte fakeBitsEOF, final byte[] treeRep, byte[] symbolBytes, final int treeRepSize) {
        MAX.putInt(METADATA_SIZE);
        MAX.put(fakeBitsTree);
        MAX.put(fakeBitsEOF);
        MAX.putInt(treeRepSize);
        MAX.put(treeRep);
        MAX.put(symbolBytes);
    }

    private static void logInfo(int METADATA_SIZE, final byte[] treeRep, byte[] symbolBytes) {
        LOG.log(Level.INFO, "MD SIZE:{0}", METADATA_SIZE);
        LOG.log(Level.INFO, "TREE REP SIZE: {0}", treeRep.length);
        LOG.log(Level.INFO, "SYMBOLS SIZE: {0}", symbolBytes.length);
    }
}
