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

        String inputFilepath = "src/main/resources/samples/test/test1.txt";
        String outputFilepath = "src/main/resources/samples/decoded_binary.txt";
        String binaryOut = inputFilepath + ".huff";
        String binaryIn = binaryOut;
    
    public IOHandlerTest() {
    }

    @Test
    public void testClass() throws IOException {
        System.out.println("encode");
        IOHandler instance = new IOHandler();
        instance.setTextInputFile(inputFilepath);
        
        instance.initialiseTextInput();
        File outputFile = new File(binaryOut);
        instance.encode();
        outputFile.delete();
        outputFile.createNewFile();
        assertTrue("File should be empty before writing: "+outputFile.length(), outputFile.length()==0);
        instance.setBinaryOutputFile(binaryOut);
        instance.initialiseBitOutput();
        instance.write();
        int metadata = instance.getEncodedMetaDataBytes();
        /**
         * Our test file has 4 symbols, and 10 times each symbol. Because we
         * have only 4 symbols, each symbol can be encoded in 2 bits. So 4 
         * symbols of 2 bits each is 8 bits == 1 byte, and 10 repetitions, so 10 bytes.
         */
        assertTrue("File should be 15 bytes. Actual: "+outputFile.length(), outputFile.length()==10+metadata);
    }

}
