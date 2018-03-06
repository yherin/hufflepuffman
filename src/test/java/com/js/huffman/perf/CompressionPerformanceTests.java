package com.js.huffman.perf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.js.huffman.io.HuffmanCompressionPerfTester;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class CompressionPerformanceTests {

    private List<String> results;

    public CompressionPerformanceTests() {
    }

    @Test
    public void testConstantKLinearN() {
        for (int i = 1; i <= 11; i++) {
            String suffix = Integer.toString(i);
            final String input = "src/test/perf/" + suffix + "lorem";
            final String output = input + ".huff";
            HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 10);
            p.run();

        }
    }

    @Test
    public void testLinearKConstantN() {
        for (int i = 1; i <= 10; i++) {
            String suffix = Integer.toString(i * 10);
            final String input = "src/test/perf/symbols" + suffix;
            final String output = input + ".huff";
            HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
            p.run();
        }
        String suffix = "200";
        final String input = "src/test/perf/symbols" + suffix;
        final String output = input + ".huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
    }
}
