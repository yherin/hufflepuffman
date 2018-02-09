/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.io.BitOutputStream;
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
    
    public HuffmanEncoder(BufferedReader reader, SymbolConverter converter) throws FileNotFoundException {
        this.reader = reader;
        this.converter = converter;
        this.stream = new BitOutputStream(new File("src/main/resources/samples/encoded_binary"));
    }

    /**
     * Encode the text file defined in this class' BufferedReader into a binary
     * file using bit shifting.
     * @see BitOutputStream
     */
    public void encodeBits(){
        
        try {
            String line = reader.readLine();
            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    writeHuffmanCodeInBits(line, i);
                    }                                               
                line = reader.readLine();
            }
            this.stream.flush();
            this.stream.close();
            logger.log(Level.INFO,"Total "+this.stream.getCount()+" bytes written.");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void writeHuffmanCodeInBits(String line, int i) {
        final char c = line.charAt(i);
        final String encodedChar = converter.characterToHuffmanCode(c);
        String msg = "Encoding "+c+" as "+encodedChar;
        logger.log(Level.INFO, msg);
        this.stream.writeCode(encodedChar);
    }
    
    public int getExtraBits(){
        return stream.getExtraBits();
    }
    
//    public void decodeBits(){
//        
//        //WORK IN PROGRESS
//        //NOT IN USE
//         try {
//            String line = reader.readLine();
//            while (line != null) {
//                for (int i = 0; i < line.length(); i++) {
//                    final char c = line.charAt(i);
//                    final String encodedChar = converter.characterToHuffmanCode(c);
//                    this.stream.writeCode(encodedChar);
//                    }
//                                               
//                line = reader.readLine();
//            }
//            this.stream.flush();
//            this.stream.close();
//            logger.log(Level.INFO,"Total "+this.stream.getCount()+" bytes written.");
//        } catch (IOException ex) {
//            logger.getLogger(HuffmanEncoder.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
