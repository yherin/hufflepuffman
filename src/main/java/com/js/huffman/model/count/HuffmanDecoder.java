/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.io.BitInputStream;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class HuffmanDecoder {

    private final BufferedWriter writer;
    private final BitInputStream stream;
    private final HuffmanTree tree;
    private boolean decodeSuccessful;
    final Logger logger = Logger.getLogger(HuffmanDecoder.class.getName());

    private int bitPos;

    public HuffmanDecoder(BufferedWriter writer, HuffmanTree tree, File inputFile, int extraBits) throws FileNotFoundException {
        this.writer = writer;
        this.stream = new BitInputStream(inputFile, extraBits);
        this.tree = tree;
        this.bitPos = 0;
    }

    public void decode() {
        while (true) {
            this.bitPos = 0;
            NodeKey[] singleByte = this.stream.readByte();
            if (singleByte != null) {
                while (this.bitPos < 8) {
                    NodeKey bit = singleByte[bitPos];
                    traverseHuffmanTree(bit);
                    if (this.decodeSuccessful) {
                        try {
                            String cs = "" + this.tree.getSymbol();
                            String msg = "Writing "+cs+" to decoded file.";
                            logger.log(Level.INFO, msg);
                            this.writer.write(cs);
                            this.decodeSuccessful = false;
                        } catch (IOException ex) {
                            logger.log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                break;
            }
        }
    }

    private void traverseHuffmanTree(NodeKey bit) {
        this.decodeSuccessful = this.tree.descend(bit);

    }
}
