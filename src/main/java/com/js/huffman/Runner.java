/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman;

import com.js.huffman.io.IOHandler;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author jack
 */
public class Runner {
    
    private IOHandler io;
    
    public Runner(){
        io = new IOHandler();
    }
    
    
    public void execute(String filepath){
        io.setFile(new File(filepath));
        io.encode();
        
    }
    
}
