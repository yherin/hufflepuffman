/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.NodeKey;
import com.js.huffman.model.structures.node.NodeType;
import java.util.Arrays;
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
    final Logger logger = Logger.getLogger(HuffmanTree.class.getName());
    private final String[] treeStringRep;
    private byte emptyBitsTreeRep;

    public HuffmanTree(PriorityQueue<Node> que) {

        this.codes = new HashMap<>(que.size());
        buildTree(que);
        this.treeStringRep = new String[2]; //[0] bit reps, [1] chars
        this.treeStringRep[0] = "";
        this.treeStringRep[1] = "";
        buildCodes(root, "", this.treeStringRep);
        //    logger.log(Level.INFO, "total symbols:" + this.codes.size());
        //    logger.log(Level.INFO, Arrays.toString(this.treeStringRep));
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
        root.setRoot();
        logger.log(Level.INFO, "Built tree successfully");
    }

    //00011 Öæ
    private void buildCodes(Node x, String huffCode, String[] huffTreeRep) {
        if (!x.hasLeft() && !x.hasRight()) {
            buildNodesBaseCase(x, huffCode, huffTreeRep);
            huffTreeRep[0] += '1'; //leaf

        } else {
            if (!x.isRoot()) {
                huffTreeRep[0] += '0';
            }

            //   }
            //   if (x.hasLeft()) {
            String leftHuffCode = huffCode + x.getLeft().getKey().toString();
            buildCodes(x.getLeft(), leftHuffCode, huffTreeRep);
            // }
            // if (x.hasRight()) {
            String rightHuffCode = huffCode + x.getRight().getKey().toString();

            buildCodes(x.getRight(), rightHuffCode, huffTreeRep);
        }

    }

    private void buildNodesBaseCase(Node x, String huffCode, String[] huffTreeRep) {
        Character symbol = x.getSymbol();
        huffTreeRep[1] += symbol;
        assert symbol != null;
        Object expectNull = this.codes.put(symbol, huffCode);
        assert expectNull == null;
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

    public String[] getTreeStringRep() {
        return treeStringRep;
    }

    public byte[] encodeTreeRepInBytes() {
        final String rep = this.treeStringRep[0];
        logger.log(Level.INFO, "Tree rep: {0}", rep);

        int bytesNeeded = calculateRequiredBytes(rep);
        this.emptyBitsTreeRep = calculateEmptyBitsInFinalTreeByte(rep);
        logger.log(Level.INFO, "Tree rep empty bits{0}", this.emptyBitsTreeRep);
        int index = 0;

        final byte[] treeBytes = new byte[bytesNeeded];
        encodeTreeRepInBytes(rep, treeBytes, index);
        return treeBytes;
    }

    private static byte calculateEmptyBitsInFinalTreeByte(final String rep) {
        if (rep.length() % 8 == 0) {
            return (byte) 0;
        } else {
            return (byte) (8 - (rep.length() % 8));
        }
    }

    private void encodeTreeRepInBytes(final String rep, final byte[] treeBytes, int index) {
        final int repSize = rep.length();
        final boolean padBits = (repSize % 8 != 0);
        for (int i = 0; i < repSize; i++) {
            /*
            If we have written 8 bits to our byte, move to the next byte.
            */
            if ((i % 8 == 0) && i != 0) { //full byte
                index++;
            }
            char bit = rep.charAt(i);
            if (bit == '0') {
                    treeBytes[index] = (byte) (treeBytes[index] << 1);
            } else {
                treeBytes[index] = (byte) ((byte) (treeBytes[index] << 1) | 1); //Shift the bit sequence left by 1 place and insert a 1. So 0001 becomes 0011
            }

            logger.log(Level.INFO, Arrays.toString(treeBytes));
           

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

    public byte getEmptyBitsTreeRep() {
        return emptyBitsTreeRep;
    }

}
