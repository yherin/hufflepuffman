package com.js.huffman.model.structures.node;

import com.sun.istack.internal.logging.Logger;
import java.util.Comparator;

/**
 * Node comparator used in NodeHeap.
 * @author jack
 */
public class NodePriorityComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        Logger logger = Logger.getLogger(NodePriorityComparator.class);
        /**
         * if o1 has smaller freq, and we want
         */
        if (o1.getFreq() < o2.getFreq()) {

            // logger.log(Level.INFO, o1+" < "+o2);
            return -1;
        }
        if (o1.getFreq().equals(o2.getFreq())) {
            //    logger.log(Level.INFO, o1+" == "+o2);

            return 0;
        } else {
            //  logger.log(Level.INFO, o1+" > "+o2);

            return 1;
        }
    }

}
