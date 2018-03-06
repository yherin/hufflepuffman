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

    private List<String> results;

    public DecompressionPerformanceTests() {
    }

//    @After
//    public void printResults() {
//
//        for (String result : results) {
//            System.out.println(result);
//        }
//    }
    @Test
    public void testEncoding100kb() {
        final String input = "src/test/perf/lorem.huff";
        final String output = "src/test/perf/lorem_decode";
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 100kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding500kb() {
        final String input = "src/test/perf/customer-500kb.xml.huff";
        final String output = "src/test/perf/customer-500kb_decode.xml";
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 100kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding1mb() {
        final String input = "src/test/perf/mondial-3.0-1mb.xml.huff";
        final String output = "src/test/perf/mondial-3.0-1mb_decode.xml";
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 1mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding2mb() {
        final String input = "src/test/perf/Tf_french2.3mb.txt.huff";
        final String output = "src/test/perf/Tf_french2.3mb_decode.txt";
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 2mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding4mb() {
        final String input = "src/test/perf/bible4.4mb.txt.huff";
        final String output = "src/test/plain/bible4.4mb_decode.txt";
        HuffmanDecompressionPerfTester p = new HuffmanDecompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        this.results = p.getResults();
    }



    @Test
    public void testConstantKLinearN() {
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