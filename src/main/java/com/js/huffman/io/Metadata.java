package com.js.huffman.io;

import java.util.logging.Logger;

/**
 * This class is an object representing the data stored in some specific
 * amount of bytes at the start of the encoded binary file.
 * @author jack
 */
public class Metadata {

    private final int METADATA_SIZE;
    private final byte[] symbolBytes;
    private final byte fakeBitsTree;
    private final byte fakeBitsEOF;
    private final byte[] treeRep;
    private static final Logger LOG = Logger.getLogger(Metadata.class.getName());

    /**
     * Create a new metadata object.
     * @param METADATA_SIZE
     * @param symbolBytes
     * @param fakeBitsTree
     * @param fakeBitsEOF
     * @param treeRep
     */
    public Metadata(int METADATA_SIZE, byte[] symbolBytes, byte fakeBitsTree, byte fakeBitsEOF, byte[] treeRep) {
        this.METADATA_SIZE = METADATA_SIZE;
        this.symbolBytes = symbolBytes;
        this.fakeBitsTree = fakeBitsTree;
        this.fakeBitsEOF = fakeBitsEOF;
        this.treeRep = treeRep;
    }

    /**
     * Return the metadata size.
     * @return the size of the metadata held in this object, in bytes.
     */
    public int getMETADATA_SIZE() {
        return METADATA_SIZE;
    }

    /**
     * Returns the symbol byte array for this metadata.
     * @return symbols byte array.
     */
    public byte[] getSymbolBytes() {
        return symbolBytes;
    }

    /**
     * Return the number of empty bits in the final byte of this metadata's
     * tree representation.
     * @return the number of fake bits in final byte.
     */
    public byte getFakeBitsTree() {
        return fakeBitsTree;
    }

    /**
     * Return the number of empty bits in the final byte at EOF.
     * @return the number of empty bits.
     */
    public byte getFakeBitsEOF() {
        return fakeBitsEOF;
    }

    /**
     * Return a byte representation of the Huffman tree stored in this metadata.
     * @return byte array representation of Huffman tree.
     */
    public byte[] getTreeRep() {
        return treeRep;
    }

}
