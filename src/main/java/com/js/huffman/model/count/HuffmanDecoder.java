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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for decoding binary and into data structure
 * @author jack
 */
public class HuffmanDecoder {

    private final BufferedWriter writer;
    private final BitInputStream stream;
    private final HuffmanTree tree;
    private boolean decodeSuccessful;
    private final static Logger logger = Logger.getLogger(HuffmanDecoder.class.getName());
    private int bitPos;

    /**
     * Create a new HuffmanDecoder object.
     * @param  writer                A @BufferedWriter object, containing the text output file.
     * @param  tree                  A @HuffmanTree object, containing the codes used by this decoder.
     * @param  extraBits             The number of extra bits in the final byte to be ignored by this decoder.
     * @param  bitInputStream        A @BitInputStream object, containing the binary input file.
     * @throws FileNotFoundException Thrown on unexepected file errors.
     */
    public HuffmanDecoder(BufferedWriter writer, HuffmanTree tree, int extraBits, BitInputStream bitInputStream) throws FileNotFoundException {
        this.writer = writer;
        this.stream = bitInputStream;
        this.tree = tree;
        this.bitPos = 0;
        this.stream.setEmptyBits(extraBits);

    }

    /**
     * Decode the binary file, writing to the output file specified in the
     * @BufferedWriter given in this class' constructor. The method is too long
     * and will be refactored later.
     */
    public void decode() {
        boolean decode = true;
        while (decode) {
            this.bitPos = 0;
            NodeKey[] singleByte = this.stream.readByte();
            if (singleByte != null) {
                while (this.bitPos < 8) {
                    NodeKey bit = singleByte[bitPos];
                    //   logger.log(Level.INFO, "bit: "+bit);
                    if (bit != NodeKey.FAKE) {
                        traverseHuffmanTree(bit);

                        if (this.decodeSuccessful) {
                            try {
                                char c = this.tree.getSymbol();
                                //       String msg = "Writing "+c+" to decoded file.";
                                //    logger.log(Level.INFO, msg);
                                this.writer.write(c);
                                this.decodeSuccessful = false;
                            } catch (IOException ex) {
                                logger.log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    this.bitPos++;
                }
            } else {
                try {
                    writer.flush();
                    writer.close();
                    break;
                } catch (IOException ex) {
                    Logger.getLogger(HuffmanDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Moves the 'head of the tree down the Huffman tree, based on the NodeKey.
     *
     */
    private void traverseHuffmanTree(NodeKey bit) {
        this.decodeSuccessful = this.tree.descend(bit);

    }
}
