/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman;

import com.js.huffman.io.IOHandler;
import java.io.File;

/**
 *
 * @author jack
 */
public class Runner {
    
    private IOHandler io;
    
    public Runner(){
        io = new IOHandler();
    }
    
    
    public void execute(){
        String inputFilepath = "src/main/resources/samples/bible.txt";
        String outputFilepath = "src/main/resources/samples/output";
        long start = System.nanoTime();
        io.setOutputFile(outputFilepath);
        io.setInputFile(inputFilepath);
        io.encode();
        io.write();
        System.out.println("Done.");
        long end = System.nanoTime();
        long elapsed = (end - start) / 1000000l;
        System.out.println("Execution time: "+elapsed+"ms.");
    }
    
}
