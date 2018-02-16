/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman;

import com.js.huffman.io.IOHandler;
import java.io.File;

/**
 *
 * @author jack
 */
public class Runner {

    private IOHandler io;

    public Runner() {
        io = new IOHandler();
    }

    public void execute() {
        String inputFilepath = "src/main/resources/samples/lorem";
        String outputFilepath = "src/main/resources/samples/decoded_binary.txt";
        String binaryOut = inputFilepath + ".huff";
        String binaryIn = binaryOut;
        long start = System.nanoTime();
        io.setTextOutputFile(outputFilepath);
        io.setTextInputFile(inputFilepath);
        io.setBinaryInputFile(binaryIn);
        io.setBinaryOutputFile(binaryOut);
        io.initialiseTextInput();
        long buildDataStart = System.nanoTime();
        io.encode();
        long buildDataEnd = System.nanoTime();
        io.initialiseBitOutput();
        long writeDataStart = System.nanoTime();
        io.write();
        long writeDataEnd = System.nanoTime();
        io.initialiseTextOutput();
        io.initialiseBitInput();
        long decompressStart = System.nanoTime();
        io.readAndDecode();
        long decompressEnd = System.nanoTime();
        System.out.println("Done.");
        long end = System.nanoTime();
        long write = (writeDataEnd - writeDataStart) / 1000000l;
        long comp = (buildDataEnd - buildDataStart) / 1000000l;
        long decomp = (decompressEnd - decompressStart) / 1000000l;
        long elapsed = (end - start) / 1000000l;
        System.out.println("Build data time: " + comp + "ms.");
        System.out.println("Write data time: " + write + "ms.");
        System.out.println("Decompression time: " + decomp + "ms.");
        System.out.println("Execution time: " + elapsed + "ms.");

    }

}
