/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class HuffmanTree {

    private final Node root;
    private Node head;
    private final HashMap<Character, String> codes; //we want to use String and not a custom class, because String is immutable. This is really handy.
    static final Logger LOG = Logger.getLogger(HuffmanTree.class.getName());
    private final String treeSymbolsRep;
    private final byte[] treeByteRep;
    private final byte emptyBitsTreeByteRep;

    public HuffmanTree(final Node root, final HashMap<Character, String> codes, final String treeSymbolsRep, final byte[] treeByteRep, final byte emptyBitsTreeByteRep) {
        this.root = root;
        this.head = root;
        this.codes = codes;
        this.treeSymbolsRep = treeSymbolsRep;
        this.treeByteRep = treeByteRep;
        this.emptyBitsTreeByteRep = emptyBitsTreeByteRep;

    }

    /**
     *
     * @param key
     * @return true if after moving the head points a leaf node. Else false.
     */
    public boolean descend(NodeKey key) {
        //  logger.log(Level.INFO, "Descend: "+key);
        navigateByKey(key);
        //  logger.log(Level.INFO, "Head: "+this.head);
        if (this.head.isLeaf()) {
            return true;
        }
        if (this.head.isBranch()) {
            return false;
        } else {
            throw new IllegalStateException("Node was neither branch nor leaf");
        }
    }

    public Character getSymbol() {
        Character symbol = this.head.getSymbol();
        this.head = this.root;
        assert (symbol != null);
        return symbol;
    }

    private void navigateByKey(NodeKey key) {
        if (key == NodeKey.ZERO) {
            Node newHead = this.head.getLeft();
            this.head = newHead;
        } else if (key == NodeKey.ONE) {
            Node newHead = this.head.getRight();
            this.head = newHead;
        }
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return codes.toString();
    }

    public HashMap<Character, String> getCodes() {
        return codes;
    }

    public String getTreeSymbolsString() {
        return treeSymbolsRep;
    }

    public byte[] getTreeByteRep() {
        return treeByteRep;
    }

    public byte getEmptyBitsTreeByteRep() {
        return emptyBitsTreeByteRep;
    }

}
