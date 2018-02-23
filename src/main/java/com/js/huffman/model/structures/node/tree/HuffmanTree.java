package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.map.HuffmanHashMap;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import java.util.logging.Logger;

/**
 * A binary-tree-like data structure. The core of Huffman coding. Symbols to be 
 * encoding are stored in leaf nodes. Branch nodes contain frequencies of their leaf symbols
 * and a binary digit. The route from leaf to root displays the encoding of the symbol.
 * @author jack
 */
public class HuffmanTree {

    private final Node root;
    private Node head;
    private final HuffmanHashMap<Character, String> codes; //we want to use String and not a custom class, because String is immutable. This is really handy.
    static final Logger LOG = Logger.getLogger(HuffmanTree.class.getName());
    private final String treeSymbolsRep;
    private final byte[] treeByteRep;
    private final byte emptyBitsTreeByteRep;

    public HuffmanTree(final Node root, final HuffmanHashMap<Character, String> codes, final String treeSymbolsRep, final byte[] treeByteRep, final byte emptyBitsTreeByteRep) {
        this.root = root;
        this.head = root;
        this.codes = codes;
        this.treeSymbolsRep = treeSymbolsRep;
        this.treeByteRep = treeByteRep;
        this.emptyBitsTreeByteRep = emptyBitsTreeByteRep;

    }

    /**
     * Move the head of the tree down left or right based on the NodeKey.
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

    public HuffmanHashMap<Character, String> getCodes() {
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
