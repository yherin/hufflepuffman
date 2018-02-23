package com.js.huffman.perf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.js.huffman.io.HuffmanCompressionPerfTester;
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

    @After
    public void printResults() {

        for (String result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void testEncoding100kb() {
        final String input = "src/main/resources/samples/test/lorem.txt";
        final String output = "src/main/resources/samples/test/lorem.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 100kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding500kb() {
        final String input = "src/main/resources/samples/test/customer-500kb.xml";
        final String output = "src/main/resources/samples/test/customer-500kb.xml.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 100kb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding1mb() {
        final String input = "src/main/resources/samples/test/mondial-3.0-1mb.xml";
        final String output = "src/main/resources/samples/test/mondial-3.0-1mb.xml.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 1mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding2mb() {
        final String input = "src/main/resources/samples/test/Tf_french2.3mb.txt";
        final String output = "src/main/resources/samples/test/Tf_french2.3mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 2mb file: ");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding4mb() {
        final String input = "src/main/resources/samples/test/bible4.4mb.txt";
        final String output = "src/main/resources/samples/bible4.4mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        this.results = p.getResults();
    }

    @Test
    public void testEncoding9mb() {
        final String input = "src/main/resources/samples/test/bible8.9mb.txt";
        final String output = "src/main/resources/samples/test/bible8.9mb.txt.huff";
        HuffmanCompressionPerfTester p = new HuffmanCompressionPerfTester(input, output, 100);
        p.run();
        System.out.println("********\n");
        System.out.println("Results for 8mb file: ");
        this.results = p.getResults();
    }
}
