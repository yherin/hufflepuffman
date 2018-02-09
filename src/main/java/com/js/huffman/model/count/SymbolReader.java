/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.model.count;

import com.js.huffman.model.structures.node.Node;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for counting frequencies of characters in a file.
 * @author jack
 */
public class SymbolReader {

    private final File file;
    private final BufferedReader reader;
    private final HashMap<Character, Integer> symbols;

    public SymbolReader(final File file) throws FileNotFoundException {
    /**
     * Create a new HuffmanReader object. Used to read text files into a 
     * Character, Count(Integer) HashMap.
     * @param file the file to be read.
     * @throws FileNotFoundException 
     */
        this.file = file;
        this.reader = new BufferedReader(new FileReader(this.file));
        this.symbols = new HashMap<>();
    }

    /**
     * Count all characters in the current file of this HuffmanReader.
     * @return HashMap<Character,Integer> all the characters in this file (k), 
     * each key's frequency (v). 
     */
    public HashMap<Character, Integer> count() {
        try {

            String readLine = reader.readLine();
            while (readLine != null) {
                for (Character c : readLine.toCharArray()) {
                    Integer current = this.symbols.get(c);
                    if (current == null) {
                        this.symbols.put(c, 1);
                    } else {
                        current++;
                        this.symbols.put(c, current);
                    }

                }
                readLine = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(SymbolReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.symbols;
    }

}