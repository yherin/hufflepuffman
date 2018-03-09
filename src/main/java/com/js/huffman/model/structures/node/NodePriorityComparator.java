package com.js.huffman.model.structures.node;
import java.util.Comparator;

/**
 * Node comparator used in NodeHeap.
 * @author jack
 */
public class NodePriorityComparator implements Comparator<Node> {

    /**
     * Compare two Nodes based on frequency.
     * @param o1
     * @param o2
     * @return 0 if equal, -1 if o2 is larger, 1 if o1 is larger.
     */
    @Override
    public int compare(Node o1, Node o2) {
        if (o1.getFreq() < o2.getFreq()) {
            return -1;
        }
        if (o1.getFreq().equals(o2.getFreq())) {
            return 0;
        } else {
            return 1;
        }
    }

}
