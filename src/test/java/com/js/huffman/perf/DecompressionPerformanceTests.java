package com.js.huffman.perf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.js.huffman.io.HuffmanDecompressionPerfTester;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class DecompressionPerformanceTests {

    public DecompressionPerformanceTests() {
        System.out.println("Testing : "+DecompressionPerformanceTests.class);
    }

    @Test
    public void testConstantKLinearN() {
        System.out.println("Test linear N, constant K");
        for (int i = 1; i <= 11; i++) {
            String suffix = Integer.toString(i);
            final String input = "src/test/perf/" + suffix + "lorem.huff";
            final String output = "src/test/perf/" + suffix + "lorem_d";
            HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 10);
            p.run();

        }
    }

    @Test
    public void testLinearKConstantN() {
        System.out.println("Test linear K, constant N.");
        for (int i = 1; i <= 10; i++) {
            String suffix = Integer.toString(i * 10);
            final String input = "src/test/perf/symbols" + suffix + ".huff";
            final String output = "src/test/perf/symbols_d" + suffix;
            HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 10);
            p.run();
        }
        String suffix = "200";
        final String input = "src/test/perf/symbols" + suffix + ".huff";
        final String output = "src/test/perf/symbols_d" + suffix;
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 10);
        p.run();

    }
}
