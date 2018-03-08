/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.heap;

import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author jack
 */
public class NodeHeapTest {
    
    /**
     * Test of poll and add methods, of class NodeHeap.
     */
    @Test
    public void testPollAndAdd() {
        System.out.println("poll");
        NodeHeap nh = new NodeHeap(new NodePriorityComparator(), 5);
        Node a = new BuiltNode('a', 5);
        nh.add(a);
        assertEquals(1,nh.getSize());
        Node polled = nh.poll();
        assertEquals(a,polled);
        assertEquals(0,nh.getSize());
    }
    
    @Test
    public void testPollExpectedOrder(){
        System.out.println("poll order");
        NodeHeap nh = new NodeHeap(new NodePriorityComparator(), 5001);
        for (int i = 5001; i >=1 ; i--) {
            nh.add(new BuiltNode('.',i));
        }
        for (int i = 5001; i <= 5; i++) {
            Node x = nh.poll();
            if (x==null){
                fail("Heap polled null");
            }
            assertEquals(i,(int)x.getFreq());
        }
    }
    
    @Test
    public void testContains(){
        
    }

    
}
