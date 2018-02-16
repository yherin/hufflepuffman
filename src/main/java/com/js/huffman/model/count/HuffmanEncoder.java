/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.io.BitOutputStream;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import com.js.huffman.model.structures.node.tree.SymbolConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author jack
 */
public class HuffmanEncoder {

    private final Logger logger = Logger.getLogger(HuffmanEncoder.class.getName());
    private final BufferedReader reader;
    private final SymbolConverter converter;
    private final BitOutputStream stream;
    private final String newLineSep;
    private final HuffmanTree tree;
    private final String treeSymbolsRep;
    private final String treeStringRep;
    
    public HuffmanEncoder(BufferedReader reader, SymbolConverter converter, BitOutputStream bitOutputStream, HuffmanTree tree) throws FileNotFoundException {
        this.reader = reader;
        this.converter = converter;
        this.stream = bitOutputStream;
        this.newLineSep = System.lineSeparator();
        this.tree = tree;
        this.treeStringRep = this.tree.getTreeStringRep()[0];
        this.treeSymbolsRep = this.tree.getTreeStringRep()[1];
    }

    /**
     * Encode the text file defined in this class' BufferedReader into a binary
     * file using bit shifting.
     * @see BitOutputStream
     */
    public void encodeBits(){
        
     ///   this.stream.writeHuffmanTree(this.treeStringRep, this.treeSymbolsRep);
        try {
           
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    final char c = line.charAt(i);
                    writeHuffmanCodeInBits(c);
                    }
                
                line = reader.readLine();
                if (line != null){
                    writeNewLineSymbolInBits();
                }
            }
            this.stream.flush();
            this.stream.close();
            logger.log(Level.INFO,"Total "+this.stream.getCount()+" bytes written.");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void writeHuffmanCodeInBits(final Character c) {
        final String encodedChar = converter.characterToHuffmanCode(c);
       // String msg = "Encoding "+c+" as "+encodedChar;
       // logger.log(Level.INFO, msg);
        this.stream.writeHuffmanCode(encodedChar);
    }
    
    private void writeNewLineSymbolInBits(){
        for (int i = 0; i < newLineSep.length(); i++) {
            final char c = this.newLineSep.charAt(i);
            writeHuffmanCodeInBits(c);
        }
    }
    
    public int getExtraBits(){
        return stream.getEndExtraBits();
    }
    

}
