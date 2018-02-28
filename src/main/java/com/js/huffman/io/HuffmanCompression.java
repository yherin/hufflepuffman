package com.js.huffman.io;

/**
 *
 * @author jack
 */
public class HuffmanCompression implements Runnable {

    protected final IOHandler io;
    protected final String outputFilepath;
    protected final String inputFilepath;

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

    protected void doCompression() {
        io.setTextInputFile(inputFilepath);
        io.setBinaryOutputFile(outputFilepath);
        io.initialiseTextInput();
        long buildDataStart = System.nanoTime();
        io.encodeToBinary();
        long buildDataEnd = System.nanoTime();
        io.initialiseBitOutput();
        long writeDataStart = System.nanoTime();
        io.writeBinaryOutput();
        long writeDataEnd = System.nanoTime();
        long write = (writeDataEnd - writeDataStart) / 1000000l;
        long comp = (buildDataEnd - buildDataStart) / 1000000l;
        System.out.println("Build data time: " + comp + "ms.");
        System.out.println("Write data time: " + write + "ms.");
    }

}
