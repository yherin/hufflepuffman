package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.map.HuffmanHashMap;

/**
 * Class for fetching the binary String encoding of a symbol.
 * @author jack
 */
public class SymbolConverter {

    private final HuffmanHashMap<Character, String> codes;

    /**
     * Create a new SymbolConverter.
     * @param codes
     */
    public SymbolConverter(final HuffmanHashMap<Character, String> codes) {
        this.codes = codes;
    }

    /**
     * Return the String representation of the Huffman code which 
     * corresponds to c.
     * @param c
     * @return string Huffman code.
     */
    public String characterToHuffmanCode(final Character c) {
        return this.codes.get(c);

    }
}
