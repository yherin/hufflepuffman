/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

/**
 *
 * @author jack
 */
public class HuffmanDecompression implements Runnable{

    private final IOHandler io;
    private final String outputFilepath;
    private final String inputFilepath;

    public HuffmanDecompression(final String inputFilepath,
            final String outputFilepath) {
        io = new IOHandler();
        this.inputFilepath = inputFilepath;
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void run() {
        doDecompression();
    }

    private void doDecompression() {
        io.setBinaryInputFile(inputFilepath);
        io.setTextOutputFile(outputFilepath);
        io.initialiseTextOutput();
        io.initialiseBitInput();
        long decompressStart = System.nanoTime();
        io.readAndDecode();
        long decompressEnd = System.nanoTime();
        System.out.println("Done.");
        long end = System.nanoTime();
        long decomp = (decompressEnd - decompressStart) / 1000000l;
        System.out.println("Decompression time: " + decomp + "ms.");
    }
}
