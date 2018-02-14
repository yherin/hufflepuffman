/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for writing individual bits to a file.
 * @author jack
 */
public class BitOutputStream extends FileOutputStream{

    private byte b = 0b0; //initial byte is empty
    private int n = 0; //the number of bits currently in our byte
    private long count = 0l; //total number of bytes written
    private int extraBits;
    final Logger logger = Logger.getLogger(BitOutputStream.class.getName());
    
    /**
     * Create a new BitOutputStreams
     * @param file the file to which output is written.
     * @throws FileNotFoundException 
     */
    public BitOutputStream(File file) throws FileNotFoundException {
        super(file);
    }
    
    /**
     * Write a Huffman code to one or more bytes. The writing process is 'continious'
     *  - that is, a code of 3 bits will be written to a single byte while allowing
     * other codes to be written to the same byte. The same applies if the code
     * is larger than 8 bits.
     * @param code 
     */
    public void writeCode(String code){
        for (int i = 0; i < code.length(); i++) {
                addSingleBit(code.charAt(i));
        }
    }
    
    /**
     * 'Add' a bit to our byte, writing the byte to file if it is a full byte.
     * @param bit 1 or 0
     * @throws IOException 
     * See https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
     * See also http://vojtechruzicka.com/bit-manipulation-java-bitwise-bit-shift-operations/
     */
    private void addSingleBit(char bit){
        if (bit == '0'){
            b = (byte) (b << 1); //Shift the bit sequence left by 1 place. So 0001 becomes 0010
        } else {
            b = (byte) ((byte) (b<<1)| 1); //Shift the bit sequence left by 1 place and insert a 1. So 0001 becomes 0011
        }
        n++;
        if (n==8){ try {
            //full byte
            super.write(b);
            
            count++;
            n=0;
            } catch (IOException ex) {
                Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Write any non-complete bytes to our file, before flushing the buffer.
     */
    @Override
    public void flush(){
        
        //TODO: Record how many 'empty' bits there are in the last byte (and ignore them). Otherwise, when we decode, we get additional characters.
        try {
            if (n>0 && n<8){
                String message = "" + (8-n) + " bits to be written in final byte.";
                logger.log(Level.INFO,message);
                extraBits = fillFinalByte();
                
            }
            super.flush();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void writeNewLine(){
        
    }

    public long getCount() {
        return count;
    }

    /**
     * Fill and write the final byte to our file, padding with 0s if necessary.
     * @return number of 'fake' bits in our final byte.
     */
    private int fillFinalByte() {

        extraBits = 8 - n;
        for (int i = 0; i < extraBits; i++) {
            addSingleBit('0');
        }
        String msg = "" + extraBits + " extra 0 bits written to byte at EOF.";
        logger.log(Level.INFO,msg);
        return extraBits;
    }

    public int getExtraBits() {
        return extraBits;
    }
            
    
    
    
    
}
