/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.map.EntryImpl;
import com.js.huffman.model.structures.map.HuffmanHashMap;
import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import java.util.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class QueueBuilderTest {

    HuffmanHashMap<Character, Integer> map;
    PriorityQueue<Node> q;
    QueueBuilder qb;

    @Before
    public void setup() {
        map = new HuffmanHashMap();
        map.put('a', 12);
        map.put('b', 9);
        map.put('c', 11);
        map.put('d', 90);
        map.put('e', 1);
        map.put('f', 6);
        qb = new QueueBuilder();
        q = qb.buildAndReturnQueue(map);

    }

    @Test
    public void queueIsCorrectLength() {
        assertTrue(this.map.size() == this.q.size());
    }

    @Test
    public void allKeyValuePairsInQueue() {
        final EntryImpl[] entries = map.keyArray();
        for (EntryImpl entry : entries) {
            inQueue((Character) entry.getKey(), (Integer) entry.getValue());
        }
    }

    private void inQueue(Character k, Integer v) {
        assertTrue(this.q.contains(new BuiltNode(k, v)));
    }

}
