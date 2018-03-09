package com.js.huffman.perf;

import com.js.huffman.action.PerfTesterHuffmanCompression;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class CompressionPerformanceTests {


    public CompressionPerformanceTests() {
        System.out.println("Testing : " + CompressionPerformanceTests.class);

    }

    @Test
    public void testConstantKLinearN() {
        System.out.println("Test constant K, linear N.");
        for (int i = 1; i <= 11; i++) {
            String suffix = Integer.toString(i);
            final String input = "src/test/perf/" + suffix + "lorem";
            final String output = input + ".huff";
            PerfTesterHuffmanCompression p = new PerfTesterHuffmanCompression(input, output, 10);
            p.run();

        }
    }

    @Test
    public void testLinearKConstantN() {
        System.out.println("Test constant N, linear K");
        for (int i = 1; i <= 10; i++) {
            String suffix = Integer.toString(i * 10);
            final String input = "src/test/perf/symbols" + suffix;
            final String output = input + ".huff";
            PerfTesterHuffmanCompression p = new PerfTesterHuffmanCompression(input, output, 10);
            p.run();
        }
        String suffix = "200";
        final String input = "src/test/perf/symbols" + suffix;
        final String output = input + ".huff";
        PerfTesterHuffmanCompression p = new PerfTesterHuffmanCompression(input, output, 10);
        p.run();
    }
}
