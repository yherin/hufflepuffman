package com.js.huffman.io;

import com.js.huffman.model.process.BitUtils;
import com.js.huffman.model.structures.node.NodeKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides functionality for writing individual bits to a file.
 *
 * @author jack
 */
public class BitInputStream extends FileInputStream {

    static final Logger LOG = Logger.getLogger(BitInputStream.class.getName());
    final long totalBytes;
    long remainingBytes;
    long readBytes;
    private int emptyBits;
    private FileChannel fc;
    private ByteBuffer buffer;
    private static final int BUFFER_SIZE = 4096;
    private Metadata data;

    /**
     * Creates a new BitInputStream, suitable for reading bits
     *
     * @param file file to be read.
     * @throws FileNotFoundException
     */
    public BitInputStream(final File file) throws FileNotFoundException {
        super(file);
        this.totalBytes = file.length();
        this.readBytes = 0l;
        this.remainingBytes = this.totalBytes;
        setFileChannel();
        handleMetadata();
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
        readBytesToBuffer();

    }

    /**
     * Get the metadata from our file. Our metadata consists of the following
     * structure: - The FIRST 4 bytes of metadata are an @int, telling the total
     * bytes of the metadata. - The 5th byte contains the number of 'empty bits'
     * in the final byte of the tree representation - The 6th byte contains the
     * number of 'empty bits' in the final byte of this file - The 7-10th byts
     * are an @int, telling the total length of the string (in bytes) which
     * represents the huffman tree. -The remaining bytes are the symbols used by
     * the huffman tree, where each symbol is encoded in UTF-8 and therefore
     * occupies 2 bytes.
     *
     * The maximum size of the metadata is currently 4096 bytes.
     *
     * @see MetadataBuilder
     * @see Metadata
     */
    private void handleMetadata() {
        try {
            this.buffer = ByteBuffer.allocate(4); //The size of our metadata in bytes is contained in the first 4 bytes of the file.
            this.fc.read(buffer);
            this.buffer.flip();
            final int MD_SIZE = this.buffer.getInt() - 4; //we read 4 bytes.
            this.readBytes += MD_SIZE + 4;
            this.remainingBytes -= MD_SIZE + 4;
            this.buffer = ByteBuffer.allocate(MD_SIZE);
            this.fc.read(buffer);
            this.buffer.flip();
            final byte TREE_REP_EMPTY_BITS = this.buffer.get();
            final byte EOF_EMPTY_BITS = this.buffer.get();
            final int TREE_REP_LENGTH = this.buffer.getInt();
            final byte[] TREE_REP = new byte[TREE_REP_LENGTH];
            this.buffer.get(TREE_REP);
            final byte[] SYMBOLS = new byte[MD_SIZE - 1 - 1 - 4 - TREE_REP_LENGTH];
            this.buffer.get(SYMBOLS);
            this.data = new Metadata(MD_SIZE, SYMBOLS, TREE_REP_EMPTY_BITS, EOF_EMPTY_BITS, TREE_REP);
        } catch (IOException ex) {
            Logger.getLogger(BitInputStream.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Read a byte contained in this ByteBuffer.
     *
     * @return a NodeKey[] representing the bits in this byte, or null if no
     * bytes are available to read.
     */
    public NodeKey[] readByte() {

        if (this.buffer.hasRemaining()) {

            return readSingleByte();
        } else {
            resetBuffer();
            int remaining = readBytesToBuffer();

            if (remaining == -1) {
                //LOG.log(Level.INFO, "EOF");
                return null;
            } else {
                return readSingleByte();

            }
        }
    }

    /**
     * Read a single byte, incrementing counters and detemining if this is the
     * final byte.
     *
     * @return NodeKey[] representing the bits in this byte.
     */
    private NodeKey[] readSingleByte() {
        byte x = this.buffer.get();
        readBytes++;
        remainingBytes--;
        // logger.log(Level.INFO, "remaning bytes: {0}", remainingBytes);
        if (remainingBytes == 0) {
            //LOG.log(Level.INFO, "FINAL BIT");
            return BitUtils.decodeBits(x, this.emptyBits);
        } else {
            return BitUtils.decodeBits(x, 0);
        }
    }

    /**
     *
     * @return the number of byts successfully read, -1 if EOF.
     */
    private int readBytesToBuffer() {
        try {
            int bytesRead = fc.read(this.buffer);
            this.buffer.flip();
            return bytesRead;
        } catch (IOException ex) {
            Logger.getLogger(BitInputStream.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    private void resetBuffer() {
        this.buffer = ByteBuffer.allocate(BUFFER_SIZE);
    }

    /**
     * Sets the quantity of 'empty bits' in the final byte.
     * These empty bits are then ignored by the BitInputStream.
     * @param emptyBits
     */
    public void setEmptyBits(int emptyBits) {
        this.emptyBits = emptyBits;
    }

    /**
     * Set the FileChannel for IO.
     */
    public final void setFileChannel() {
        this.fc = this.getChannel();
    }

    /**
     * Fetch the metadata associated with this BitInputStream
     * @return metadata
     * @see Metadata
     */
    public Metadata getData() {
        return data;
    }

}
