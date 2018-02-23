/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.structures.node.tree;

import com.js.huffman.model.structures.map.HuffmanHashMap;
import java.util.HashMap;

/**
 *
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
