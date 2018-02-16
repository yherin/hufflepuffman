/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class Metadata {
    
    private final int METADATA_SIZE;
    private final byte[] symbolBytes;
    private final byte fakeBitsTree;
    private final byte fakeBitsEOF;
    private final byte[] treeRep;
    private static final Logger LOG = Logger.getLogger(Metadata.class.getName());

    
    public Metadata(int METADATA_SIZE, byte[] symbolBytes, byte fakeBitsTree, byte fakeBitsEOF, byte[] treeRep) {
        this.METADATA_SIZE = METADATA_SIZE;
        this.symbolBytes = symbolBytes;
        this.fakeBitsTree = fakeBitsTree;
        this.fakeBitsEOF = fakeBitsEOF;
        this.treeRep = treeRep;
    }

    public int getMETADATA_SIZE() {
        return METADATA_SIZE;
    }

    public byte[] getSymbolBytes() {
        return symbolBytes;
    }

    public byte getFakeBitsTree() {
        return fakeBitsTree;
    }

    public byte getFakeBitsEOF() {
        return fakeBitsEOF;
    }

    public byte[] getTreeRep() {
        return treeRep;
    }
    
    
    
    
    
}