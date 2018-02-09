/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.model.count.HuffmanDecoder;
import com.js.huffman.model.count.HuffmanEncoder;
import com.js.huffman.model.count.SymbolReader;
import com.js.huffman.model.process.QueueBuilder;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import com.js.huffman.model.structures.node.tree.SymbolConverter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IO utility class.
 *
 * @author jack
 */
public class IOHandler {

    private final QueueBuilder queueBuilder;
    private SymbolConverter converter;
    private HuffmanEncoder encoder;
    private HuffmanDecoder decoder;
    private HuffmanTree puu;
    private final InputFileHandler inputHandler;
    private final OutputFileHandler outputHandler;
    private final Logger logger = Logger.getLogger(IOHandler.class.getName());
    private  BufferedReader reader;
    private  BufferedWriter writer;
    
    public IOHandler() {
        queueBuilder = new QueueBuilder();
        inputHandler = new InputFileHandler();
        outputHandler = new OutputFileHandler();
    }

    

    public void initialiseInput() {
        try {
            this.reader = new BufferedReader(new FileReader(this.inputHandler.getFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void initialiseOutput(){
        try {
            this.writer = new BufferedWriter(new FileWriter(this.outputHandler.getFile()));
        } catch (IOException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Write the given file to a binary file, based on the create Huffman tree.
     */
    public void write() {
        converter = new SymbolConverter(puu.getCodes());
        try {
            encoder = new HuffmanEncoder(this.reader, converter);
            encoder.encodeBits();
            logger.log(Level.INFO, "Encoding done.");
            
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void readAndDecode(){
        try {
            int extraBits = encoder.getExtraBits();
            decoder = new HuffmanDecoder(this.writer, puu, new File("src/main/resources/samples/encoded_binary"), extraBits);
            decoder.decode();
            logger.log(Level.INFO, "Decoding done.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Encodes the c
     */
    public void encode() {
        HashMap<Character, Integer> map = read();
        PriorityQueue<Node> nodes = queueBuilder.buildAndReturnQueue(map);
        this.puu = new HuffmanTree(nodes);
        System.out.println(puu);

    }

    /**
     * Read the symbols from a given file into a symbol HashMap for processing.
     *
     * @return HashMap<@Character,@Integer>
     */
    private HashMap<Character, Integer> read() {
        if (!inputHandler.isReady()) {
            throw new UnsupportedOperationException("Define a file to be read before attempting to read.");
        }
        SymbolReader reader;
        try {
            reader = new SymbolReader(inputHandler.getFile());
            return reader.count();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("Error when reading input from file " + inputHandler.getFile().toString());

        }
    }

    /**
     * Set the output file to the file at the given path.
     *
     * @param path
     */
    public void setOutputFile(final String path) {
        this.outputHandler.setFile(path);
    }

    /**
     * Set the input file to the file at the given path.
     *
     * @param path
     */
    public void setInputFile(final String path) {
        this.inputHandler.setFile(path);
    }

    /**
     * Fetch a new BufferedReader using the currently set input file.
     *
     * @return
     */



    public HuffmanTree getPuu() {
        return puu;
    }

    
    
}
