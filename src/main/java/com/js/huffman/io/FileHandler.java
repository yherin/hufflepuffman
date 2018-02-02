/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.File;

/**
 * Abstract file handling class.
 * @see InputFileHandler
 * @see OutputFileHandler
 * @author jack
 */
public abstract class FileHandler {
    
          
    public abstract void setFile(String absolutePath); 
        

    public abstract File getFile();
    

    public abstract boolean hasFileSet();
    
    public abstract boolean isReady();

}
