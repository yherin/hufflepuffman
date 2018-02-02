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
 *
 * @author jack
 */
public class HuffmanDecoder {

    private final BufferedWriter writer;
    private final BitInputStream stream;
    private final HuffmanTree tree;
    
    
    public HuffmanDecoder(BufferedWriter writer, BitInputStream stream, HuffmanTree tree) throws FileNotFoundException {
        this.writer = writer;
        this.stream = stream;
        this.tree = tree;
    }
    
    
    
    public void decode(){
        
    }
    
    private void decodeHuffmanString(NodeKey[] bits){
        for (NodeKey n : bits){
            boolean descended = tree.descend(n);
            if (!descended){
                char c = tree.getSymbol();
                try {
                    writer.write(c);
                } catch (IOException ex) {
                    Logger.getLogger(HuffmanDecoder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
}
