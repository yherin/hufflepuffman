package com.js.huffman.io;

/**
 *
 * @author jack
 */
public class HuffmanDecompression implements Runnable {

    protected final IOHandler io;
    protected final String outputFilepath;
    protected final String inputFilepath;

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

    protected void doDecompression() {
        io.setBinaryInputFile(inputFilepath);
        io.setTextOutputFile(outputFilepath);
        io.initialiseTextOutput();
        io.initialiseBitInput();
        long decompressStart = System.nanoTime();
        io.readBinaryInputAndDecode();
        long decompressEnd = System.nanoTime();
        System.out.println("Done.");
        long end = System.nanoTime();
        long decomp = (decompressEnd - decompressStart) / 1000000l;
        System.out.println("Decompression time: " + decomp + "ms.");
    }
}
