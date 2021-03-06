package com.js.huffman.io;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

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
            test_dir = "src/test/plain/";
            this.file = new File(test_dir + "out");
            this.streamer = new BitOutputStream(file);
            this.streamer.setFileChannel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BitOutputStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void bitStreamerWritesCorrectly() {
        try {
            String code = "10010110"; //1 byte
//            streamer.writeMetadata(new byte[1], "abca", (byte)0);
            streamer.writeHuffmanCode(code);
            streamer.flush();
            streamer.close();
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStreamTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        long x = this.file.length() + this.streamer.getMetadataBytes();
        assertTrue(x == 1);
    }

}
