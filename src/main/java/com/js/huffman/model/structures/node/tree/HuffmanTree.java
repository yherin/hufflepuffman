/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class HuffmanTree {

    private Node root;
    private Node head;
    private final HashMap<Character, String> codes; //we want to use String and not a custom class, because String is immutable. This is really handy.

    public HuffmanTree(PriorityQueue<Node> que) {
        
        this.codes = new HashMap<>(que.size());
        buildTree(que);
        buildCodes(root,"");
        System.out.println("total symbols:" + this.codes.size());
        this.head = root;
    }

    private void buildTree(PriorityQueue<Node> que) {

        while (que.size() >= 2) {
            Node firstNode = que.poll();
            Node secondNode = que.poll();

            Node joint = new Node(firstNode, secondNode);
            que.add(joint);
        }
        root = que.poll();
        Logger.getLogger(HuffmanTree.class.getName()).log(Level.INFO, "Built tree successfully");
    }


    private void buildCodes(Node x, String huffCode) {
        if (!x.hasLeft() && !x.hasRight()) {
            buildNodesBaseCase(x, huffCode);
        }
        if (x.hasLeft()){
            String leftHuffCode = huffCode + x.getLeft().getKey().toString();
            buildCodes(x.getLeft(),leftHuffCode);
        }
        if (x.hasRight()){
            String rightHuffCode = huffCode + x.getRight().getKey().toString();
            buildCodes(x.getRight(), rightHuffCode);
        }

    }

    private void buildNodesBaseCase(Node x, String huffCode) {
        Character symbol = x.getSymbol();
        assert symbol != null;
        Object expectNull = this.codes.put(symbol, huffCode);
        assert expectNull == null;
    }
    
    /**
     * 
     * @param key
     * @return true if the head was successfully moved to a child Node, false otherwise.
     */
    public boolean descend(NodeKey key){
        if (this.head.isLeaf()){
            return false;
        }
        if (this.head.isBranch()){
            navigateByKey(key);
            return true;
        }
        else return false;
    }
    
    public Character getSymbol(){
        Character symbol =  this.head.getSymbol();
        this.head = this.root;
        return symbol;        
    }
    
    private void navigateByKey(NodeKey key){
        if (key == NodeKey.ZERO){
            Node newHead = this.head.getLeft();
            this.head = newHead;
        } else if (key == NodeKey.ONE){
            Node newHead = this.head.getRight();
            this.head = newHead;
        }
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString(){
       return codes.toString();
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }
    
    
}
