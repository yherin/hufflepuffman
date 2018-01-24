/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.process;

import com.js.huffman.model.process.NodeBuilder;
import com.js.huffman.model.structures.node.Node;
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
public class NodeBuilderTest {
    
    public NodeBuilderTest() {
    }
    

    @Test
    public void nodeBuilderReturnsValidNode(){
       Node a = NodeBuilder.buildLeafNode('@', 1);
       doAssertions(a, new Node ('@',1));
    }
    
    private void doAssertions(Node x, Node node){
        assertNotNull(x);
        assertTrue(x.getClass() == Node.class);
        assertEquals(x, node);
    }
}