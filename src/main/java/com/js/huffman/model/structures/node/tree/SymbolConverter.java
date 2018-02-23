package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.map.HuffmanHashMap;

/**
 * Class for fetching the binary String encoding of a symbol.
 * @author jack
 */
public class SymbolConverter {

    private final HuffmanHashMap<Character, String> codes;

    public SymbolConverter(final HuffmanHashMap<Character, String> codes) {
        this.codes = codes;
    }

    public String characterToHuffmanCode(final Character c) {
        return this.codes.get(c);

    }
}
