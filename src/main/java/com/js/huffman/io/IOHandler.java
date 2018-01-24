/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.model.count.HuffmanReader;
import com.js.huffman.model.process.QueueBuilder;
import com.js.huffman.model.process.TreeBuilder;
import com.js.huffman.model.structures.node.Node;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jack
 */
public class IOHandler {
    
    private File currentFile;
    private TreeBuilder treeBuilder;
    private QueueBuilder queueBuilder;
    
    
    public IOHandler(){
        currentFile = null;
        queueBuilder = new QueueBuilder();
    }
    
    public void write(){
        throw new UnsupportedOperationException("NYI.");
    }
    
    public HashMap<Character, Integer> read(){
        if (currentFileIsNull()){
            throw new UnsupportedOperationException("Define a file to be read before attempting to read.");
        }
        HuffmanReader reader;
        try {
            reader = new HuffmanReader(currentFile);
            return reader.count();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnsupportedOperationException("Error when reading input from file "+currentFile.toString());

        }
    }
    
    public void setFile(final File file){
        currentFile = file;
    }
    
    public boolean currentFileIsNull(){
        return this.currentFile == null;
    }

    public void encode() {
        HashMap<Character,Integer> map = read();
        PriorityQueue<Node> nodes = queueBuilder.buildAndReturnQueue(map);
        HuffmanTree puu = new HuffmanTree(nodes);
        
    }
    
}
