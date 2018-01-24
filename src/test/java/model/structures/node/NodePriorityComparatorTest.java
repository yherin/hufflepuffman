/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.structures.node;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodePriorityComparator;
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
public class NodePriorityComparatorTest {
    
    PriorityQueue<Node> nodes;
    
    public NodePriorityComparatorTest() {
        this.nodes = new PriorityQueue<>(new NodePriorityComparator());
    }
    
    
    @Before
    public void setUp() {
    
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void lowestCountFirst(){
        nodes.add(new Node('a', 1));
        nodes.add(new Node('b',2));
        nodes.add(new Node('x',312));
        Node x = nodes.poll();
        Node y = nodes.poll();
        assertTrue("Comparator sorted the order of nodes incorrectly", x.getFreq() <= y.getFreq());
        assertTrue(x.getFreq()==1);
        assertTrue(y.getFreq()==2);
    }
    

}
