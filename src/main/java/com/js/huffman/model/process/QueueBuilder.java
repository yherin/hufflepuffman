/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import com.sun.istack.internal.logging.Logger;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;

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
    public final PriorityQueue<Node> buildAndReturnQueue(final HashMap<Character, Integer> map) {
        map.forEach((k, v) -> buildNodeAndAddToQueue(k, v));
        return this.que;
    }

    private void buildNodeAndAddToQueue(final Character key, final Integer value) {
        final Node n = new Node(key, value);
        que.add(n);
    }

}
