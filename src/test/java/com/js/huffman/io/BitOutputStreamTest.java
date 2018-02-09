package com.js.huffman.io;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.js.huffman.io.BitOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jack
 */
public class BitOutputStreamTest {
    
    BitOutputStream streamer;
    String test_dir;
    File file;
    
    public BitOutputStreamTest() {
        try {
            test_dir = "src/main/resources/samples/test/";
            this.file = new File(test_dir + "out");
            this.streamer = new BitOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BitOutputStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void bitStreamerWritesCorrectly(){
        try {
            String code = "10010110"; //1 byte
            streamer.writeCode(code);
            streamer.flush();
            streamer.close();
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertTrue(this.file.length()==1);
        
    }
    
}
