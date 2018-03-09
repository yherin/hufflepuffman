package com.js.huffman.action;

import com.js.huffman.action.PerfTesterHuffmanCompression;
import com.js.huffman.action.HuffmanDecompression;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class is used ONLY for performance testing. It is not used in the normal
 * running of the application.
 *
 * @author jack
 */
public class PerfTesterHuffmanDecompression extends HuffmanDecompression {

    private final int iterations;
    private static final Logger LOG = Logger.getLogger(PerfTesterHuffmanCompression.class.getName());
    private List<String> results;

    /**
     * Create a compression performance tester.
     * @param inputFilepath
     * @param outputFilepath
     * @param iterations
     */
    public PerfTesterHuffmanDecompression(String inputFilepath, String outputFilepath, int iterations) {
        super(inputFilepath, outputFilepath);
        this.iterations = iterations;
        this.results = new ArrayList<>();
    }

    /**
     * Run the performance test.
     */
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

    /**
     *
     * @return
     */
    public List<String> getResults() {
        return results;
    }
}
