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
 *
 * @author jack
 */
public class HuffmanReader {

    private final File file;
    private final BufferedReader reader;
    private final HashMap<Character,Integer> symbols;

    public HuffmanReader(final File file) throws FileNotFoundException {
        this.file = file;
        this.reader = new BufferedReader(new FileReader(this.file));
        this.symbols = new HashMap<>();
    }

    public HashMap<Character, Integer> count() {
        try {

            String readLine = reader.readLine();
            while (readLine != null) {
                for (Character c : readLine.toCharArray()) {
                     Integer current = this.symbols.get(c);
                     if (current == null){
                         this.symbols.put(c, 1);
                     } else {
                         current++;
                         this.symbols.put(c, current);
                     }
                     
                }
                readLine = reader.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(HuffmanReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.symbols;
    }

}
