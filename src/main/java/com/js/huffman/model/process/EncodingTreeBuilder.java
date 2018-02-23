/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.process;

import com.js.huffman.model.structures.map.HuffmanHashMap;
import com.js.huffman.model.structures.node.BuiltNode;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.NodeType;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class EncodingTreeBuilder implements TreeBuilder {

    Node root;
    private static final Logger LOG = Logger.getLogger(EncodingTreeBuilder.class.getName());
    private byte emptyBitsTreeRep;
    private final HuffmanHashMap<Character, String> codes;
    private final PriorityQueue<Node> que;
    private String treeStringRep;
    private String treeSymbols;
    private byte[] treeByteRep;

    public EncodingTreeBuilder(PriorityQueue<Node> que) {
        codes = new HuffmanHashMap<>();
        this.que = que;
        this.treeStringRep = "";
        this.treeSymbols = "";
    }

    @Override
    public HuffmanTree buildTree() {
        this.root = buildTreeStructure(que);
        this.root.setRoot();
        buildCodes(root, "");
        this.treeByteRep = encodeTreeBytes(this.treeStringRep);
        this.root.setKeyCode(NodeKey.FAKE);
        HuffmanTree tree = new HuffmanTree(root, codes, treeSymbols, treeByteRep, emptyBitsTreeRep);
        return tree;
    }

    private static byte calculateEmptyBitsInFinalTreeByte(final String rep) {
        if (rep.length() % 8 == 0) {
            return (byte) 0;
        } else {
            return (byte) (8 - (rep.length() % 8));
        }
    }

    //    public HuffmanTree(final Node root){
    //        this.root = root;
    //        this.head = root;
    //    }
    private Node buildTreeStructure(PriorityQueue<Node> que) {
        while (que.size() >= 2) {
            Node firstNode = que.poll();
            Node secondNode = que.poll();
            Node joint = new BuiltNode(firstNode, secondNode);
            que.add(joint);
        }
        root = que.poll();
        root.setType(NodeType.BRANCH);
        LOG.log(Level.INFO, "Built tree successfully");
        return root;
    }

    //00011 Öæ
    private void buildCodes(Node x, String huffCode) {
        if (!x.hasLeft() && !x.hasRight()) {
            buildNodesBaseCase(x, huffCode);
            this.treeStringRep += '1'; //leaf
        } else {
            if (!x.isRoot()) {
                this.treeStringRep += '0';
            }
            //   }
            //   if (x.hasLeft()) {
            String leftHuffCode = huffCode + x.getLeft().getKey().toString();
            buildCodes(x.getLeft(), leftHuffCode);
            // }
            // if (x.hasRight()) {
            String rightHuffCode = huffCode + x.getRight().getKey().toString();
            buildCodes(x.getRight(), rightHuffCode);
        }
    }

    private void buildNodesBaseCase(Node x, String huffCode) {
        Character symbol = x.getSymbol();
        this.treeSymbols += symbol;
        assert symbol != null;
        Object expectNull = codes.put(symbol, huffCode);
        assert expectNull == null;
    }

    private int calculateRequiredBytes(final String rep) {
        int bytesNeeded = 0;
        if (rep.length() <= 8) {
            bytesNeeded = 1;
        } else {
            if (rep.length() % 8 == 0) {
                bytesNeeded = (rep.length() / 8);
            } else {
                bytesNeeded = (rep.length() / 8) + 1;
            }
        }
        return bytesNeeded;
    }

    private byte[] encodeTreeBytes(final String rep) {

//        LOG.log(Level.INFO, "Tree rep: {0}", rep);
        int bytesNeeded = calculateRequiredBytes(rep);
        this.emptyBitsTreeRep = calculateEmptyBitsInFinalTreeByte(rep);
//        LOG.log(Level.INFO, "Tree rep empty bits{0}", emptyBitsTreeRep);
        int index = 0;
        final byte[] treeBytes = new byte[bytesNeeded];
        encodeTreeRepInBytes(rep, treeBytes, index);
        return treeBytes;
    }

    private void encodeTreeRepInBytes(final String rep, final byte[] treeBytes, int index) {
        final int repSize = rep.length();
        final boolean padBits = repSize % 8 != 0;
        for (int i = 0; i < repSize; i++) {
            /*
            If we have written 8 bits to our byte, move to the next byte.
             */
            if ((i % 8 == 0) && i != 0) {
                //full byte
                index++;
            }
            char bit = rep.charAt(i);
            if (bit == '0') {
                treeBytes[index] = (byte) (treeBytes[index] << 1);
            } else {
                treeBytes[index] = (byte) ((byte) (treeBytes[index] << 1) | 1); //Shift the bit sequence left by 1 place and insert a 1. So 0001 becomes 0011
            }
//            LOG.log(Level.INFO, Arrays.toString(treeBytes));
        }
        /* If we have excess bits, pad the bits to make a full byte.
         * E.g. our tree rep is 10 characters, each character corresponding
        to 1 bit. We need 2 bytes, but only 10 bits. So 8 bits in the final byte
        are 'fake'/'empty'
         */
        if (padBits) {
            final int bitsToPad = 8 - (repSize % 8);
            final int byteIndex = treeBytes.length - 1;
            treeBytes[byteIndex] = (byte) (treeBytes[index] << bitsToPad);
        }
    }

}
