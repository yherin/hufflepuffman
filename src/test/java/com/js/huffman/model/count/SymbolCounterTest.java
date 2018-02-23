/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.model.count.SymbolReader;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SymbolCounterTest {

    private final String test_dir;

    private File empty;
    private File numbers;
    private File spaces;
    private File symbols;
    private File tenThousandCharacters;

    public SymbolCounterTest() {
        this.test_dir = "src/main/resources/samples/test/";
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.empty = new File(test_dir + "testEmpty.txt");
        this.numbers = new File(test_dir + "numbers");
        this.spaces = new File(test_dir + "spaces");
        this.symbols = new File(test_dir + "symbols");
        this.tenThousandCharacters = new File(test_dir + "large.txt");
    }

    private SymbolReader createCounter(File file) {
        SymbolReader sc;
        try {
            sc = new SymbolReader(file);
            return sc;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SymbolCounterTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Failed to create a symbol counter");
    }

    @Test
    public void emptyFile() {
        SymbolReader sc = createCounter(this.empty);
        HashMap<Character, Integer> map = sc.count();
        assertTrue("HashMap should be empty after counting empty file", map.isEmpty());
    }

    @Test
    public void onlySymbols() {
        SymbolReader sc = createCounter(this.symbols);
        HashMap<Character, Integer> map = sc.count();
        Object[] chars = map.keySet().toArray();
        for (Object c : chars) {
            String s = c.toString();
            assertTrue("Only symbols should be present in the map", s.matches("[^a-zA-Z0-9]"));
        }
    }

    @Test
    public void onlyNumbers() {
        SymbolReader sc = createCounter(this.numbers);
        HashMap<Character, Integer> map = sc.count();
        Object[] chars = map.keySet().toArray();
        for (Object c : chars) {
            String s = c.toString();
            assertTrue("Only numbers should be present in the map", s.matches("\\d"));
        }
    }

}
