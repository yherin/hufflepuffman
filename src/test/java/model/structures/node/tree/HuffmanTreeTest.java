/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structures.node.tree;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.util.PriorityQueue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class HuffmanTreeTest {
    
    PriorityQueue<Node> nodes;
    NodePriorityComparator comp;
    
    public HuffmanTreeTest() {
    comp = new NodePriorityComparator();
    nodes = new PriorityQueue<>(comp);
    }
    
    
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
        nodes.clear();
    }

    
    @Test
    public void rootFrequencyEqualsTotalFrequency(){
        nodes.add(new Node('a', 30));
        nodes.add(new Node('b', 30));
        nodes.add(new Node('c', 30));
        nodes.add(new Node('d', 30));
        HuffmanTree x = new HuffmanTree(nodes);
        assertTrue(x.getRoot().getFreq() == 120);
    }
}
