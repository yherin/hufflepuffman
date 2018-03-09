package com.js.huffman.model.process;

import com.js.huffman.model.structures.node.tree.HuffmanTree;

/**
 * Interface for TreeBuilder implementations
 * @author jack
 */
public interface TreeBuilder {

    /**
     * Return the built HuffmanTree.
     * @return the HuffmanTree.
     */
    public abstract HuffmanTree buildTree();

}
