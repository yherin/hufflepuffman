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
 *
 * @author jack
 */
public class QueueBuilder {
    
    private PriorityQueue<Node> que;
    
    public QueueBuilder(){
        que = new PriorityQueue<>(new NodePriorityComparator());
    }
    
    
    public PriorityQueue<Node> buildAndReturnQueue(HashMap<Character, Integer> map){
        map.forEach((k,v) -> buildNodeAndAddToQueue(k, v));
        return this.que;
    }

    private void buildNodeAndAddToQueue(Character key, Integer value){
        Node n = new Node(key,value);
        que.add(n);
    }
    
}
