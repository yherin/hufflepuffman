/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Jack
 */
public class IOHandlerTest {

    String inputFilepath = "src/test/plain/lorem";
    String outputFilepath = "src/test/plain/decoded_binary.txt";
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
        File binaryOutputFile = new File(binaryOut);
        instance.encodeToBinary();
        binaryOutputFile.delete();
        binaryOutputFile.createNewFile();
        assertTrue("File should be empty before writing: " + binaryOutputFile.length(), binaryOutputFile.length() == 0);
        instance.setBinaryOutputFile(binaryOut);
        instance.initialiseBitOutput();
        instance.writeBinaryOutput();
        File textOutputFile = new File(outputFilepath);
        textOutputFile.delete();
        textOutputFile.createNewFile();
        instance.setBinaryInputFile(binaryIn);
        instance.setTextOutputFile(outputFilepath);
        assertTrue("File should be empty before writing: " + textOutputFile.length(), textOutputFile.length() == 0);
        assertFalse(FileUtils.contentEquals(instance.getOriginalFile(), instance.getDecompressedFile()));
        instance.initialiseBitInput();
        instance.initialiseTextOutput();
        /**
         * Our test file has 4 symbols, and 10 times each symbol. Because we
         * have only 4 symbols, each symbol can be encoded in 2 bits. So 4
         * symbols of 2 bits each is 8 bits == 1 byte, and 10 repetitions, so 10
         * bytes.
         */
        instance.readBinaryInputAndDecode();
        assertTrue(FileUtils.contentEquals(instance.getOriginalFile(), instance.getDecompressedFile()));
    }

}
