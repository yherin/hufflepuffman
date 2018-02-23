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
import com.js.huffman.model.structures.node.NodePriorityComparator;
import java.util.PriorityQueue;

/**
 * Class which builds PriorityQueue<Node>.
 *
 * @author jack
 */
public class QueueBuilder {

    private final PriorityQueue<Node> que;

    public QueueBuilder() {
        que = new PriorityQueue<>(new NodePriorityComparator());
    }

    /**
     * Constructs a PriorityQueue<Node> from HashMap<Character, Integer>.
     *
     * @param map the HashMap<K,V> of characters and their frequencies.
     * @return PriorityQueue<Node> the created Queue.
     */
    public final PriorityQueue<Node> buildAndReturnQueue(final HuffmanHashMap<Character, Integer> map) {
        final EntryImpl[] entries = map.keyArray();
        for (EntryImpl entry : entries) {
            buildNodeAndAddToQueue((Character) entry.getKey(), (Integer) entry.getValue());
        }
        return this.que;
    }

    private void buildNodeAndAddToQueue(final Character key, final Integer value) {
        final Node n = new BuiltNode(key, value);
        que.add(n);
    }

}
