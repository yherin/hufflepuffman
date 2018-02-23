/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.io.BitInputStream;
import com.js.huffman.io.IOHandler;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class HuffmanDecoderTest {

    private final String binary = "src/main/resources/samples/encoded_binary";

    private final IOHandler io;

    public HuffmanDecoderTest() {
        io = new IOHandler();
        io.setTextInputFile("src/main/resources/samples/test/test1.txt");

    }

    @Before
    public void setUp() {
        io.initialiseTextInput();
        io.encode();
        io.setBinaryOutputFile(binary);
        io.initialiseBitOutput();
        io.write();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of decode method, of class HuffmanDecoder.
     */
//    @Test
//    public void testDecode() {
//        try {
//            System.out.println("decode");
//            HuffmanDecoder instance = new HuffmanDecoder(new BufferedWriter(new FileWriter(new File("src/main/resources/samples/decoded_binary.txt"))), io.getDecoded_tree(),  0, new BitInputStream(new File(binary)));
//            instance.decode();
//            
//        } catch (IOException ex) {
//            Logger.getLogger(HuffmanDecoderTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
