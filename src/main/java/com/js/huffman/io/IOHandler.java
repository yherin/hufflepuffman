/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.model.count.HuffmanDecoder;
import com.js.huffman.model.count.HuffmanEncoder;
import com.js.huffman.model.count.SymbolReader;
import com.js.huffman.model.process.DecodingTreeBuilder;
import com.js.huffman.model.process.EncodingTreeBuilder;
import com.js.huffman.model.process.QueueBuilder;
import com.js.huffman.model.structures.map.HuffmanHashMap;
import com.js.huffman.model.structures.node.heap.NodeHeap;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import com.js.huffman.model.structures.node.tree.SymbolConverter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * IO utility class.
 *
 * @author jack
 */
public class IOHandler {

    //Proccessing
    private final QueueBuilder queueBuilder;
    private SymbolConverter converter;
    private HuffmanEncoder encoder;
    private HuffmanDecoder decoder;
    private HuffmanTree encoded_tree;
    private HuffmanTree decoded_tree;
    private final InputFileHandler inputHandler;
    private final OutputFileHandler outputHandler;
    private final Logger logger = Logger.getLogger(IOHandler.class.getName());

    //IO
    private BufferedReader reader;
    private BufferedWriter writer;
    private BitInputStream bitInputStream;
    private BitOutputStream bitOutputStream;
    private File binaryOutputFile;
    private File binaryInputFile;
    private int extraBits;

    public IOHandler() {
        queueBuilder = new QueueBuilder();
        inputHandler = new InputFileHandler();
        outputHandler = new OutputFileHandler();

    }

    /**
     * Initialise the BufferedReader used by this file.
     */
    public void initialiseTextInput() {
        try {
            this.reader = new BufferedReader(new FileReader(this.inputHandler.getFile()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("no text input");
        }
    }

    /**
     * Initialise the BufferedWriter used by this file.
     */
    public void initialiseTextOutput() {
        try {
            this.writer = new BufferedWriter(new FileWriter(this.outputHandler.getFile()));
        } catch (IOException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("failed to init text output");

        }

    }

    /**
     * Initialise the BitOutputStream used by this file.
     */
    public void initialiseBitOutput() {
        try {
            this.bitOutputStream = new BitOutputStream((this.binaryOutputFile));
            this.bitOutputStream.setFileChannel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("failed to init binary output file");

        }
    }

    /**
     * Initialise the BitInputStream used by this file.
     */
    public void initialiseBitInput() {
        try {
            this.bitInputStream = new BitInputStream((this.binaryInputFile));
        } catch (FileNotFoundException ex) {
           Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("failed to init binary input file");

        }
    }

    /**
     * Write the data held within the encoded huffman tree to the currently set
     * output file. All files and IO utilities must be initialised.
     */
    public void writeBinaryOutput() {
        try {
            converter = new SymbolConverter(encoded_tree.getCodes());
            encoder = new HuffmanEncoder(this.reader, converter, this.bitOutputStream, this.encoded_tree);
            encoder.encodeBits();
            //     logger.log(Level.INFO, "IO: Writing done.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read and decode binary data from the currently defined binary input file,
     * writing it in decoded format to a text file.
     */
    public void readBinaryInputAndDecode() {
        try {
            Metadata md = fetchMetadata();
            this.extraBits = md.getFakeBitsEOF();
            DecodingTreeBuilder dtb = new DecodingTreeBuilder(md);
            this.decoded_tree = dtb.buildTree();
            decoder = new HuffmanDecoder(this.writer, this.decoded_tree, this.extraBits, this.bitInputStream);
            decoder.decode();
            //       logger.log(Level.INFO, "Decoding done.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Encodes the c
     */
    public void encodeToBinary() {
        HuffmanHashMap<Character, Integer> map = read();
        logger.log(Level.INFO, map.toString());
        NodeHeap nodes = queueBuilder.buildAndReturnQueue(map);
        EncodingTreeBuilder etb = new EncodingTreeBuilder(nodes);
        this.encoded_tree = etb.buildTree();

    }

    /**
     * Read the symbols from a given file into a symbol HashMap for processing.
     *
     * @return HashMap<@Character,@Integer>
     */
    private HuffmanHashMap<Character, Integer> read() {
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
    public void setTextOutputFile(final String path) {
        this.outputHandler.setFile(path);
    }

    /**
     * Set the input file to the file at the given path.
     *
     * @param path
     */
    public void setTextInputFile(final String path) {
        this.inputHandler.setFile(path);
    }

    public void setBinaryOutputFile(String path) {
        this.binaryOutputFile = new File(path);
    }

    public void setBinaryInputFile(final String path) {
        this.binaryInputFile = new File(path);
    }

    /**
     * Fetch a new BufferedReader using the currently set input file.
     *
     * @return
     */
    public HuffmanTree getEncoded_tree() {
        return encoded_tree;
    }

    public HuffmanTree getDecoded_tree() {
        return decoded_tree;
    }

    public int getEncodedMetaDataBytes() {
        return this.bitOutputStream.getMetadataBytes();
    }

    public Metadata fetchMetadata() {
        Metadata md = this.bitInputStream.getData();
        return md;
    }

    public File getOriginalFile() {
        return this.inputHandler.getFile();
    }

    public File getDecompressedFile() {
        return this.outputHandler.getFile();
    }

}
