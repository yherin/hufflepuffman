package com.js.huffman.action;

import com.js.huffman.io.IOHandler;

/**
 * This class is provides the functionality to perform a decompression operation.
 *
 * @author jack
 */
public class HuffmanDecompression implements Runnable {

    protected final IOHandler io;
    protected final String outputFilepath;
    protected final String inputFilepath;

    /** Create a new HuffmanDecompression object, representing a single compression
     * operation.
     * @param inputFilepath
     * @param outputFilepath
     */
    public HuffmanDecompression(final String inputFilepath,
            final String outputFilepath) {
        io = new IOHandler();
        this.inputFilepath = inputFilepath;
        this.outputFilepath = outputFilepath;
    }

    /**
     * Perform the decompression operation.
     */
    @Override
    public void run() {
        doDecompression();
    }

    protected void doDecompression() {
        io.setBinaryInputFile(inputFilepath);
        io.setTextOutputFile(outputFilepath);
        io.initialiseTextOutput();
        io.initialiseBitInput();
//        long decompressStart = System.nanoTime();
        io.readBinaryInputAndDecode();
//        long decompressEnd = System.nanoTime();
//        System.out.println("Done.");
//        long end = System.nanoTime();
//        long decomp = (decompressEnd - decompressStart) / 1000000l;
//        System.out.println("Decompression time: " + decomp + "ms.");
    }
}
