package com.js.huffman.model.structures.node.heap;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;


/**
 *
 * @author jack
 */
public class NodeHeap {
    
    private final static int TOP = 1;
    final Node[] heap;
    final int initialCapacity;
    int capacity;
    final double loadFactor = 0.75f;
    int count;
    private final NodePriorityComparator npc;

    public NodeHeap(final NodePriorityComparator npc, final int capacity){
        this.npc = npc;
        this.initialCapacity = this.capacity + 2; //index starting at 1, 1 additional slot for operations
        this.heap = new Node[this.initialCapacity];
        this.count = 0;
    }



    public Node poll(){
        return heap[1];
    }
    
    
//    public Node add(){
//        
//    }
    
}
