package com.js.huffman.io;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is used ONLY for performance testing. It is not used in the normal
 * running of the application.
 *
 * @author jack
 */
public class HuffmanDecompressionPerfTester extends HuffmanDecompression {

    private final int iterations;
    private static final Logger LOG = Logger.getLogger(HuffmanCompressionPerfTester.class.getName());
    private List<String> results;

    public HuffmanDecompressionPerfTester(String inputFilepath, String outputFilepath, int iterations) {
        super(inputFilepath, outputFilepath);
        this.iterations = iterations;
        this.results = new ArrayList<>();
    }

    @Override
    public void run() {
        doDecompression();
    }

    @Override
    protected void doDecompression() {
        long totalTime = 0L;
        for (int i = 0; i < iterations; i++) {
            io.setBinaryInputFile(inputFilepath);
            io.setTextOutputFile(outputFilepath);
            io.initialiseTextOutput();
            io.initialiseBitInput();
            long decompressStart = System.nanoTime();
            io.readBinaryInputAndDecode();
            long decompressEnd = System.nanoTime();
            long decomp = (decompressEnd - decompressStart) / 1000000l;
            totalTime += decomp;
            results.add("Decompression time (incl. IO): " + decomp + "ms.");
        }
      //  results.add("total time: " + totalTime+"ms");
      //  results.add("average time over " + iterations + " iterations: " + (totalTime / iterations)+"ms");
        System.out.println("Average : " + ((totalTime) / iterations) + "ms");

    }

    public List<String> getResults() {
        return results;
    }
}
