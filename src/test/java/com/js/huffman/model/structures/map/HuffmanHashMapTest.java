/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.map;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class HuffmanHashMapTest {

    public HuffmanHashMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of size method, of class HuffmanHashMap.
     */
    @Test
    public void testSizeAndEmpty() {
        System.out.println("size, empty");
        HuffmanHashMap<Character, String> instance = new HuffmanHashMap<>();
        assertEquals(0, instance.size());
        assertTrue(instance.isEmpty());
        instance.put('a', "asdf");
        assertEquals(1, instance.size());
        assertFalse(instance.isEmpty());
        instance.put('a', "qwerty");
        assertEquals(1, instance.size());
        instance.put('b', "a");
        instance.put('c', "1");
        instance.put('d', "¨**");
        instance.put('ä', "aadfw2214124124");
        instance.put('x', ".");
        assertEquals(6, instance.size());

    }

    /**
     * Test of put method, of class HuffmanHashMap.
     */
    @Test
    public void testPutChaining() {
        System.out.println("put");
        HuffmanHashMap<Character, Integer> instance = new HuffmanHashMap<>(1);
        Object null1 = instance.put('a', 1);
        assertNull(null1);
        Object one = instance.put('a', 2);
        assertEquals(1, one);
        Object null2 = instance.put('b', 2);
        assertNull(null2);
        Object two = instance.put('b', 3);
        assertEquals(2, two);
    }

    /**
     * Test of put method, of class HuffmanHashMap.
     */
    @Test
    public void testPut() {
        HuffmanHashMap<Integer, Integer> instance1 = new HuffmanHashMap<>();
        HuffmanHashMap<Integer, Integer> instance2 = new HuffmanHashMap<>();
        for (int i = 0; i < 10000; i++) {
            assertNull(instance1.put(i, i));
        }

        for (int i = 0; i < 10000; i++) {
            if (i == 0) {
                assertNull(instance2.put(1, i));
            } else {
                assertEquals((int) i - 1, (int) instance2.put(1, i));
            }
        }
    }

    @Test
    public void testContainsKey() {
        HuffmanHashMap<Integer, Integer> instance1 = new HuffmanHashMap<>();
        boolean shouldBeFalse = instance1.containsKey(1);
        assertFalse(shouldBeFalse);
        instance1.put(2, 3);
        boolean shouldStillBeFalse = instance1.containsKey(1);
        assertFalse(shouldStillBeFalse);
        instance1.put(1, 0);
        boolean shouldBeTrue = instance1.containsKey(1);
        assertTrue(shouldBeTrue);
    }

    @Test
    public void testKeyArraySimple() {
        HuffmanHashMap<String, Integer> map = new HuffmanHashMap<>();
        map.put("10", 10);
        Object[] keys = map.keyArray();
        assertEquals(1, keys.length);
        EntryImpl en = (EntryImpl) keys[0];
        assertEquals(10, en.getValue());
    }

    @Test
    public void testKeyArrayMany() {
        HuffmanHashMap<String, Integer> map = new HuffmanHashMap<>();
        Random rand = new Random();
        int q = 1500;
        for (int i = 0; i < q; i++) {
            int n = rand.nextInt();
            map.put(Integer.toString(n), n);
        }
        Object[] keys = map.keyArray();
        assertEquals(q, keys.length);
        for (int i = 0; i < q; i++) {
            EntryImpl en = (EntryImpl) keys[i];
            if (en == null){
                fail();
            }
            if (en.getKey() == null){
                fail();
            }
            Object result = map.get((String) en.getKey());
            if (result == null){
                fail();
            }
            assertEquals(en.getValue(), map.get((String) en.getKey()));
        }
    }

}
