/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.io.Metadata;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.ReconstructedNode;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Build a HuffmanTree based on the metadata of the decoded binary file.
 * @see Metadata
 * 
 * NOT YET FULLY IMPLEMENTED.
 * 
 * @author jack
 */
public class TreeBuilder {

    private final Metadata data;
    private final String decoded_symbols;
    private final NodeKey[] nodeKeys;
   // private final ReconstructedNode root;
    private static final Logger LOG = Logger.getLogger(TreeBuilder.class.getName());

    public TreeBuilder(final Metadata data) {
        this.data = data;
        this.decoded_symbols = convertSymbolsToUTF8();
        LOG.log(Level.INFO, (this.decoded_symbols));
        this.nodeKeys = convertBitsToNodeKeys();
        LOG.log(Level.INFO, (Arrays.toString(this.nodeKeys)));

        // this.root = buildHuffmanTree(nodeKeys, decoded_symbols);
        //LOG.log(Level.INFO, this.root.toString());
    }

    /**
     * Convert our byte array into a string, encoded in UTF-8. This string holds
     * the symbols in the huffman tree that we will construct.
     * @return String of symbols.
     */
    private String convertSymbolsToUTF8() {
        try {
            return new String(this.data.getSymbolBytes(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TreeBuilder.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Convert our byte array (representing the huffman tree in encoded form)
     * into an array NodeKey, with each element representing an individual bit.
     * @return NodeKey[] for all bytes in our byte array, each NodeKey element 
     * representing a single bit.
     * @see NodeKey
     */
    private NodeKey[] convertBitsToNodeKeys() {
        final byte[] treeAsBytes = data.getTreeRep();
        NodeKey[] keys = new NodeKey[treeAsBytes.length * 8];
        int index = 0;
        for (int i = 0; i < treeAsBytes.length; i++) {
            NodeKey[] singleByte = BitUtils.decodeBits(treeAsBytes[i], 0);
            for (NodeKey nodeKey : singleByte) {
                keys[index] = nodeKey;
                index++;
            }
        }
        return keys;
    }

    ///NOT YET IMPLEMENTED FULLY
    
//    private ReconstructedNode buildHuffmanTree(final NodeKey[] bits, final String symbols) {
//        /**
//         * Node ReadNode(BitReader reader) { if (reader.ReadBit() == 1) { return
//         * new Node(reader.ReadByte(), null, null); } else { Node leftChild =
//         * ReadNode(reader); Node rightChild = ReadNode(reader); return new
//         * Node(0, leftChild, rightChild); } }
//         */
//
//        ReconstructedNode root = buildNodes(bits, symbols, 0, 0);
//        return root;
//    }
//
//    private ReconstructedNode buildNodes(final NodeKey[] bits, final String symbols, Integer i, Integer j) {
//        if (bits[i] == NodeKey.ONE) {
//            i++;
//            int x = j;
//            j++;
//            return new ReconstructedNode(symbols.charAt(x));
//
//        } else {
//            i++;
//            ReconstructedNode left = buildNodes(bits, symbols, i, j);
//            i++;
//            ReconstructedNode right = buildNodes(bits, symbols, i, j);
//            return new ReconstructedNode(left, right);
//        }
//    }

}
