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
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for writing individual bits to a file.
 *
 * @author jack
 */
public class BitOutputStream extends FileOutputStream {

    private byte b = 0b0; //initial byte is empty
    private int n = 0; //the number of bits currently in our byte
    private long count = 0l; //total number of bytes written
    private int endExtraBits;
    private int headerExtraBits;
    private int metadataBytes;
    final Logger logger = Logger.getLogger(BitOutputStream.class.getName());
    private FileChannel fc;
    private ByteBuffer buffer;
    private static final int BUFFER_SIZE = 4096;

    /**
     * Create a new BitOutputStreams
     *
     * @param file the file to which output is written.
     * @throws FileNotFoundException
     */
    public BitOutputStream(final File file) throws FileNotFoundException {
        super(file);
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        
        

    }

    public final void setFileChannel() {
        this.fc = this.getChannel();
    }

    /**
     * Write a Huffman code to one or more bytes. The writing process is
     * 'continious' - that is, a code of 3 bits will be written to a single byte
     * while allowing other codes to be written to the same byte. The same
     * applies if the code is larger than 8 bits.
     *
     * @param code
     */
    public void writeHuffmanCode(final String code) {
        for (int i = 0; i < code.length(); i++) {
            addSingleBit(code.charAt(i));
        }
    }

    /**
     * Write our metadata to our binary file before the encoded data.
     * @param treeRep byte representation of our huffman tree
     * @param symbols String of all symbols in the huffman tree.
     * @param emptyTreeBits no. of empty bits in the final byte of @treeRep
     * @see MetadataBuilder
     */
    public void writeMetadata(final byte[] treeRep, final String symbols, final byte emptyTreeBits) {
        final ByteBuffer metadata = MetadataBuilder.buildMetadataBuffer(treeRep, symbols, emptyTreeBits);
        this.buffer.put(metadata);
        this.buffer.flip();
        this.metadataBytes = metadata.limit();
        try {
            this.fc.write(this.buffer);
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        
    }
    
    /**
     * Not yet in use. Seems to cause NPE. Needs more debugging.
     */
    private void writeNumberOfEmptyEOFBitsToMetaData(){
        try {
            final long pos = this.fc.position();
            this.fc.position(0x5); //5th byte contains this data
            logger.log(Level.INFO, "Trying to write {0} to byte at index {1}", new Object[]{this.endExtraBits, 0x5});
            ByteBuffer bits = ByteBuffer.allocate(1);
            assert this.endExtraBits<8;
            bits.put((byte)this.endExtraBits);
            bits.flip();
            this.fc.write(bits);
            this.fc.position(pos);
        } catch (IOException ex) {
            Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    /**
     * 'Add' a bit to our byte, writing the byte to file if it is a full byte.
     *
     * @param bit 1 or 0
     * @throws IOException See
     * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html See
     * also
     * http://vojtechruzicka.com/bit-manipulation-java-bitwise-bit-shift-operations/
     */
    private void addSingleBit(char bit) {
        if (bit == '0') {
            b = (byte) (b << 1); //Shift the bit sequence left by 1 place. So 0001 becomes 0010
        } else {
            b = (byte) ((byte) (b << 1) | 1); //Shift the bit sequence left by 1 place and insert a 1. So 0001 becomes 0011
        }
        n++;
        if (n == 8) { //full byte
            addByte(b);
            count++;
            n = 0;
        }
    }

    private void addByte(final byte x) {
        if (this.buffer.hasRemaining()) {
       //        logger.log(Level.INFO, "Added byte to buffer");
            this.buffer.put(x);
        } else {
            //   logger.log(Level.INFO, "Writing buffer to file");
            try {
                this.buffer.flip();
                this.fc.write(this.buffer);

                this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
                this.buffer.put(x);
                //  logger.log(Level.INFO, "Added byte to buffer");
            } catch (IOException ex) {
                Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void forceWriteBuffer() {
        if (this.buffer.limit() != this.buffer.remaining()) {
            try {
                logger.log(Level.INFO, "Forcing buffer to file with "+buffer.position()+" bytes");
                this.buffer.flip();
                this.fc.write(this.buffer);

            } catch (IOException ex) {
                Logger.getLogger(BitOutputStream.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            logger.log(Level.INFO, "Buffer was empty when forced.");
        }
    }

    /**
     * Write any non-complete bytes to our file, before flushing the buffer.
     */
    @Override
    public void flush() {

        try {
            if (n > 0 && n < 8) {
                String message = "" + (8 - n) + " bits to be written in final byte.";
                logger.log(Level.INFO, message);
                endExtraBits = (byte) fillFinalByte();
//                writeNumberOfEmptyEOFBitsToMetaData();

            } else {
                forceWriteBuffer();
            }
            this.fc.close();
            super.flush();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public long getCount() {
        return count;
    }

    /**
     * Fill and write the final byte to our file, padding with 0s if necessary.
     *
     * @return number of 'fake' bits in our final byte.
     */
    private int fillFinalByte() {

        endExtraBits = 8 - n;
        for (int i = 0; i < endExtraBits; i++) {
            addSingleBit('0');
        }
        String msg = "" + endExtraBits + " extra 0 bits written to byte at EOF.";
        logger.log(Level.INFO, msg);
        forceWriteBuffer();
        return endExtraBits;
    }

    public int getEndExtraBits() {
        return endExtraBits;
    }

    public int getMetadataBytes() {
        return metadataBytes;
    }

    
}
