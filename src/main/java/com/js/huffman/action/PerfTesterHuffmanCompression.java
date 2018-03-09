package com.js.huffman.action;

import com.js.huffman.action.HuffmanCompression;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is used ONLY for performance testing. It is not used in the normal
 * running of the application.
 *
 * @author jack
 */
public final class PerfTesterHuffmanCompression extends HuffmanCompression {

    private final int iterations;
    private final List<String> results;

    /**
     * Create a compression performance tester.
     * @param inputFilepath
     * @param outputFilepath
     * @param iterations
     */
    public PerfTesterHuffmanCompression(String inputFilepath, String outputFilepath, int iterations) {
        super(inputFilepath, outputFilepath);
        this.iterations = iterations;
        this.results = new ArrayList<>();
    }

    /**
     * Run the performance test.
     */
    @Override
    public final void run() {
        doCompression();
    }

    @Override
    protected final void doCompression() {
        long totalBuildTime = 0L;
        for (int i = 0; i < iterations; i++) {
            io.setTextInputFile(inputFilepath);
            io.setBinaryOutputFile(outputFilepath);
            io.initialiseTextInput();

            long buildDataStart = System.nanoTime();
            io.encodeTree();
            io.initialiseBitOutput();
            io.writeBinaryOutput();
            long buildDataEnd = System.nanoTime();
            long build = (buildDataEnd - buildDataStart) / 1000000l;
            this.results.add("Algorithm processing time: " + build + "ms.");
            totalBuildTime += build;
        }
       // this.results.add("Total processing time over" + iterations + " iterations: " + (totalBuildTime) + "ms");
        System.out.println("Total over "+iterations+ " iterations: "+ totalBuildTime+"ms"); 
       System.out.println("Average : " + ((totalBuildTime) / iterations) + "ms");

        long writeDataStart = System.nanoTime();
        long writeDataEnd = System.nanoTime();
        long write = (writeDataEnd - writeDataStart) / 1000000l;

    }

    /**
     * Get the results of this performance tester.
     * @return the results.
     */
    public List<String> getResults() {
        return results;
    }

}
