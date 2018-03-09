package com.js.huffman.model.process;

import com.js.huffman.model.structures.map.EntryImpl;
import com.js.huffman.model.structures.map.HuffmanHashMap;
import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import com.js.huffman.model.structures.node.heap.NodeHeap;

/**
 * Class which builds NodeHeap objects.
 *
 * @author jack
 */
public class NodeHeapBuilder {

    private NodeHeap que;

    /**
     * Constructs a PriorityQueue<Node> from HashMap<Character, Integer>.
     *
     * @param map the HashMap<K,V> of characters and their frequencies.
     * @return PriorityQueue<Node> the created Queue.
     */
    public final NodeHeap buildAndReturnQueue(final HuffmanHashMap<Character, Integer> map) {
        que = new NodeHeap(new NodePriorityComparator(), map.size());
        final EntryImpl[] entries = map.entryArray();
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
