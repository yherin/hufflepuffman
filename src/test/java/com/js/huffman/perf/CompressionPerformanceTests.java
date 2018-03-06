package com.js.huffman.perf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.js.huffman.io.HuffmanCompressionPerfTester;
import com.js.huffman.io.HuffmanDecompressionPerfTester;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class CompressionPerformanceTests {

    private List<String> results;

    public CompressionPerformanceTests() {
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
        final String input = "src/test/perf/lorem.txt";
        final String output = "src/test/perf/lorem.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 100kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding500kb() {
        final String input = "src/test/perf/customer-500kb.xml";
        final String output = "src/test/perf/customer-500kb.xml.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 500kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding1mb() {
        final String input = "src/test/perf/mondial-3.0-1mb.xml";
        final String output = "src/test/perf/mondial-3.0-1mb.xml.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 1mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding2mb() {
        final String input = "src/test/perf/Tf_french2.3mb.txt";
        final String output = "src/test/perf/Tf_french2.3mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 2mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding4mb() {
        final String input = "src/test/perf/bible4.4mb.txt";
        final String output = "src/test/perf/bible4.4mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding9mb() {
        final String input = "src/test/perf/bible8.9mb.txt";
        final String output = "src/test/perf/bible8.9mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 8mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testConstantKExponentialN() {
        for (int i = 1; i <= 11; i++) {
            String suffix = Integer.toString(i);
            final String input = "src/test/perf/lorem" + suffix;
            final String output = input + ".huff";
            HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 10);
            p.run();

        }
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
