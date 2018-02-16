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

    public static ByteBuffer buildMetadata(final byte[] treeRep, final String symbols, final byte fakeBitsTree, final byte fakeBitsEOF) {

        final int MAX_METADATA_SIZE = 4096;
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
        METADATA_SIZE = symbolBytes.length + treeRep.length + 4 + 1 + 1; //4 = this int, 1 is fakeBytesTree, 1 is fakeBytesEOF
        putData(MAX, METADATA_SIZE, fakeBitsTree, fakeBitsEOF, treeRep, symbolBytes);
        logInfo(METADATA_SIZE, treeRep, symbolBytes);
        MAX.flip();
        return MAX;
    }

    private static void putData(final ByteBuffer MAX, int METADATA_SIZE, final byte fakeBitsTree, final byte fakeBitsEOF, final byte[] treeRep, byte[] symbolBytes) {
        MAX.putInt(METADATA_SIZE);
        MAX.put(fakeBitsTree);
        MAX.put(fakeBitsEOF);
        MAX.put(treeRep);
        MAX.put(symbolBytes);
    }

    private static void logInfo(int METADATA_SIZE, final byte[] treeRep, byte[] symbolBytes) {
        LOG.log(Level.INFO, "MD SIZE:{0}", METADATA_SIZE);
        LOG.log(Level.INFO, "TREE REP SIZE: {0}", treeRep.length);
        LOG.log(Level.INFO, "SYMBOLS SIZE: {0}", symbolBytes.length);
    }
}
