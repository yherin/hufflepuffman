/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.node.Node;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class HuffmanTree {
    
    Node root;
    
    
    public HuffmanTree(PriorityQueue<Node> que){
        construct(que);
    }
    
    private void construct(PriorityQueue<Node> que) {
    
        while (que.size() >=2){
            Node firstNode = que.poll();
            Node secondNode = que.poll();

            Node joint = new Node(firstNode, secondNode);
            que.add(joint);
        }
        
        root = que.poll();
        Logger.getLogger(HuffmanTree.class.getName()).log(Level.INFO, root.toString());
    }

    public Node getRoot() {
        return root;
    }
    
    
    
}
