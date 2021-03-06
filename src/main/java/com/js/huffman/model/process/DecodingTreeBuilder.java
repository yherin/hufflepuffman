package com.js.huffman.model.process;

import com.js.huffman.io.Metadata;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.ReconstructedNode;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides functionality to build a Huffman tree from the Metadata
 * stored in the encoded binary files.
 *
 * @see Metadata
 * @author jack
 */
public class DecodingTreeBuilder implements TreeBuilder {

    private final Metadata data;
    private final String decoded_symbols;
    private final NodeKey[] nodeKeys;
    private static final Logger LOG = Logger.getLogger(DecodingTreeBuilder.class.getName());
    private int nodeKeyIndex;
    private int symbolsIndex;
    private Node root;

    /**
     * Create a new DecodingTreeBuilder, which can build HuffmanTree objects
     * based on metadata.
     * @param data
     */
    public DecodingTreeBuilder(final Metadata data) {
        this.nodeKeyIndex = 0;
        this.symbolsIndex = 0;
        this.data = data;
        this.decoded_symbols = convertSymbolsToUTF8();
        this.nodeKeys = convertBitsToNodeKeys();
    }
    
    /**
     * Build the tree from Metadata.
     * @return the built HuffmanTree object.
     * @see HuffmanTree
     */
    @Override
    public HuffmanTree buildTree() {
        this.root = buildTreeAndReturnRoot();
        HuffmanTree tree = new HuffmanTree(root, null, this.decoded_symbols, this.data.getTreeRep(), this.data.getFakeBitsTree());
        return tree;
    }

    private Node buildTreeAndReturnRoot() {
        final Node node = new ReconstructedNode(NodeKey.FAKE);
        node.setLeft(build());
        ReconstructedNode right = build();
        node.setRight(right);
        node.setRoot();
        return node;
    }

    /**
     * Convert our byte array into a string, encoded in UTF-8. This string holds
     * the symbols in the huffman tree that we will construct.
     *
     * @return String of symbols.
     */
    private String convertSymbolsToUTF8() {
        try {
            return new String(this.data.getSymbolBytes(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(DecodingTreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Convert our byte array (representing the huffman tree in encoded form)
     * into an array NodeKey, with each element representing an individual bit.
     *
     * @return NodeKey[] for all bytes in our byte array, each NodeKey element
     * representing a single bit.
     * @see NodeKey
     */
    private NodeKey[] convertBitsToNodeKeys() {
        final byte[] treeAsBytes = data.getTreeRep();
        NodeKey[] keys = new NodeKey[treeAsBytes.length * 8];
        NodeKey[] singleByte;
        int index = 0;
        for (int i = 0; i < treeAsBytes.length; i++) {
            if (i == treeAsBytes.length - 1) { //final byte
                singleByte = BitUtils.decodeBits(treeAsBytes[i], this.data.getFakeBitsTree());
            } else {
                singleByte = BitUtils.decodeBits(treeAsBytes[i], 0);
            }
            for (NodeKey nodeKey : singleByte) {
                keys[index] = nodeKey;
                index++;
            }
        }
        return keys;
    }

    private ReconstructedNode build() {
        NodeKey k = getNextKey();
        if (k == NodeKey.ONE) {
            return new ReconstructedNode(getNextSymbol());
        } else if (k == NodeKey.ZERO) {
            ReconstructedNode c = new ReconstructedNode(NodeKey.ZERO);
            ReconstructedNode l = build();
            ReconstructedNode r = build();
            c.setLeft(l);
            c.setRight(r);
            return c;
        } else {
            return null;
        }
    }

    private NodeKey getNextKey() {
        if (this.nodeKeyIndex < this.nodeKeys.length) {
            NodeKey x = this.nodeKeys[this.nodeKeyIndex];
            this.nodeKeyIndex++;
            return x;
        } else {
            return null;
        }
    }

    private Character getNextSymbol() {
        Character c = this.decoded_symbols.charAt(this.symbolsIndex);
        this.symbolsIndex++;
        return c;
    }

}
