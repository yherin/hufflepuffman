/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.io.BitOutputStream;
import com.js.huffman.model.count.HuffmanEncoder;
import com.js.huffman.model.structures.node.tree.SymbolConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class HuffmanEncoderTest {
    
    private final BufferedReader textReader;
    private final FileInputStream binaryReader;
    private final SymbolConverter converter;
    private final File out = new File("src/main/resources/samples/encoded_binary");
    private final Logger logger = Logger.getLogger(HuffmanEncoderTest.class.getName());
    
    public HuffmanEncoderTest() throws FileNotFoundException {
       
            this.textReader = new BufferedReader(new FileReader("src/main/resources/samples/test/test1.txt"));
            this.converter = new SymbolConverter(buildCodes());
            this.binaryReader = new FileInputStream(out);
    }
    
    
    private HashMap<Character, String> buildCodes(){
        //the write to be encoded will contain 10 of each character
        HashMap<Character, String> hashMap = new HashMap<>();
        hashMap.put('a', "10"); //2 bits
        hashMap.put('b',"01"); // 2 bits
        hashMap.put('c',"110"); // 3 bits
        hashMap.put('d',"01110"); // 5 bits
                                //total 12 bits * 10 = 120 bits = (120 / 8 bytes) = 15
        return hashMap;
    }
    

    /**
     * Test of encodeBits method, of class HuffmanEncoder.
     * @throws java.io.IOException
     */
    @Test
    public void testEncodeBits() throws IOException {
        long expectedBytes = 15l; //see constructor for arbitrary number of bytes
        System.out.println("encodeBits");
        final BitOutputStream bitOutputStream = new BitOutputStream(out);
        bitOutputStream.setFileChannel();
        HuffmanEncoder instance = new HuffmanEncoder(this.textReader, this.converter, bitOutputStream);
        
        instance.encodeBits();
        long size = out.length();
        assertTrue(""+size +"!="+expectedBytes, size == expectedBytes );
        byte xB = (byte) this.binaryReader.read();
        byte yB = (byte) this.binaryReader.read();
        /**
         * Java will always cast @byte to @int, so we need this naughty string formatting hack.
         */
        String x = String.format("%8s", Integer.toBinaryString(xB & 0xFF)).replace(' ', '0');
        assertTrue("First byte of output not as expected - 10011100 !="+x, "10011100".equals(x));
        String y = String.format("%8s", Integer.toBinaryString(yB & 0xFF)).replace(' ', '0');
        assertTrue("Second byte of output not as expected", "11101001".equals(y));
        /**
         * After the second byte, the sequence is just repetition. The correctness of the size of the output is already tested in the first assertion.
         */
    }
    }
