/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.Node;
import java.util.HashMap;

/**
 *
 * @author jack
 */
public class NodeBuilder {

    public static Node buildLeafNode(final Character c, final Integer i){
        Node n = new Node(c,i);
        return n;
    }
    
}
