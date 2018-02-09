/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class IOHandlerTest {

    final String output = "src/main/resources/samples/output";
    final String input = "src/main/resources/samples/test/test1.txt";
    final File out = new File(output);
    final File in = new File(input);
    
    public IOHandlerTest() {
    }

    @Test
    public void testClass() throws IOException {
        System.out.println("encode");
        IOHandler instance = new IOHandler();
        instance.setInputFile(input);
        instance.setOutputFile(output);
        out.delete();
        out.createNewFile();
        instance.encode();
        assertTrue("File should be empty before writing: "+out.length(), out.length()==0);
        instance.write();
        /**
         * Our test file has 4 symbols, and 10 times each symbol. Because we
         * have only 4 symbols, each symbol can be encoded in 2 bits. So 4 
         * symbols of 2 bits each is 8 bits == 1 byte, and 10 repetitions, so 10 bytes.
         */
        assertTrue("File should be 15 bytes. Actual: "+out.length(), out.length()==10);
    }

}
