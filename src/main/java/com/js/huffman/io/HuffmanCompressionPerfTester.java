/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is used ONLY for performance testing. It is not used in the normal 
 * running of the application.
 * @author jack
 */
public class HuffmanCompressionPerfTester extends HuffmanCompression {

    private final int iterations;
    private static final Logger LOG = Logger.getLogger(HuffmanCompressionPerfTester.class.getName());
    private List<String> results;

    public HuffmanCompressionPerfTester(String inputFilepath, String outputFilepath, int iterations) {
        super(inputFilepath, outputFilepath);
        this.iterations = iterations;
        this.results = new ArrayList<>();
    }

    @Override
    public void run() {
        doCompression();
    }
    
    @Override
    protected void doCompression() {
        long totalBuildTime = 0L;
        for (int i = 0; i < iterations; i++) {
            io.setTextInputFile(inputFilepath);
            io.setBinaryOutputFile(outputFilepath);
            io.initialiseTextInput();

            long buildDataStart = System.nanoTime();
            io.encodeToBinary();
            long buildDataEnd = System.nanoTime();
            long build = (buildDataEnd - buildDataStart) / 1000000l;
            this.results.add("Algorithm processing time: " + build + "ms.");
            totalBuildTime += build;
        }
        this.results.add("Total processing time over" + iterations + " iterations: " + (totalBuildTime) + "ms");
        this.results.add("Average : " + ((totalBuildTime) / iterations) + "ms");

        io.initialiseBitOutput();
        long writeDataStart = System.nanoTime();
        io.writeBinaryOutput();
        long writeDataEnd = System.nanoTime();
        long write = (writeDataEnd - writeDataStart) / 1000000l;

        this.results.add("IO writing time (One operation): " + write + "ms.");
    }

    public List<String> getResults() {
        return results;
    }

}
