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
import com.js.huffman.model.structures.node.heap.NodeHeap;

/**
 * Class which builds NodeHeap.
 *
 * @author jack
 */
public class QueueBuilder {

    private NodeHeap que;

    /**
     * Constructs a PriorityQueue<Node> from HashMap<Character, Integer>.
     *
     * @param map the HashMap<K,V> of characters and their frequencies.
     * @return PriorityQueue<Node> the created Queue.
     */
    public final NodeHeap buildAndReturnQueue(final HuffmanHashMap<Character, Integer> map) {
        que = new NodeHeap(new NodePriorityComparator(), map.size());
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
