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
public class HuffmanCompression implements Runnable {

    private final IOHandler io;
    private final String outputFilepath;
    private final String inputFilepath;

    public HuffmanCompression(final String inputFilepath,
            final String outputFilepath) {
        io = new IOHandler();
        this.inputFilepath = inputFilepath;
        this.outputFilepath = outputFilepath;
    }

    @Override
    public void run() {
        doCompression();
    }

    private void doCompression() {
        io.setTextInputFile(inputFilepath);
        io.setBinaryOutputFile(outputFilepath);
        io.initialiseTextInput();
        long buildDataStart = System.nanoTime();
        io.encode();
        long buildDataEnd = System.nanoTime();
        io.initialiseBitOutput();
        long writeDataStart = System.nanoTime();
        io.write();
        long writeDataEnd = System.nanoTime();
        long write = (writeDataEnd - writeDataStart) / 1000000l;
        long comp = (buildDataEnd - buildDataStart) / 1000000l;
        System.out.println("Build data time: " + comp + "ms.");
        System.out.println("Write data time: " + write + "ms.");
    }

}
