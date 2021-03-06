package com.js.huffman.model.count;

import com.js.huffman.io.BitOutputStream;
import com.js.huffman.model.structures.node.tree.HuffmanTree;
import com.js.huffman.model.structures.node.tree.SymbolConverter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for encoding data in the Huffman tree into binary.
 *
 * @author jack
 */
public class HuffmanEncoder {

    private static final Logger logger = Logger.getLogger(HuffmanEncoder.class.getName());
    private final BufferedReader reader;
    private final SymbolConverter converter;
    private final BitOutputStream stream;
    private final String newLineSep;
    private HuffmanTree tree;
    private String treeSymbolsRep;
    private byte[] treeBytes;
    private byte treeRepEmptyBits;

    /**
     * Create a new HuffmanEncoder, used to encode to binary.
     * @param reader
     * @param converter
     * @param bitOutputStream
     * @param tree
     * @throws FileNotFoundException
     */
    public HuffmanEncoder(final BufferedReader reader,
            final SymbolConverter converter,
            final BitOutputStream bitOutputStream, final HuffmanTree tree)
            throws FileNotFoundException {
        this.reader = reader;
        this.converter = converter;
        this.stream = bitOutputStream;
        this.newLineSep = System.lineSeparator();
        this.tree = tree;
        //our test class isn't using a real tree.
        if (this.tree != null) {
            this.treeBytes = this.tree.getTreeByteRep();
            this.treeSymbolsRep = this.tree.getTreeSymbolsString();
            this.treeRepEmptyBits = this.tree.getEmptyBitsTreeByteRep();
        }
    }

    /**
     * Encode the text file defined in this class' BufferedReader into bytes.
     *
     * @see BitOutputStream
     */
    public void encodeBits() {

        try {
            if (this.tree != null) {
                //write the metadata header to the file.
                this.stream.writeMetadata(this.treeBytes, treeSymbolsRep, (byte) this.treeRepEmptyBits);
            }
            String line = reader.readLine();
            while (line != null) {
                //while there are lines in the file, encode each character
                for (int i = 0; i < line.length(); i++) {
                    final char c = line.charAt(i);
                    writeHuffmanCodeInBits(c);
                }

                line = reader.readLine();
                if (line != null) {
                    writeNewLineSymbolInBits();
                }
            }
            this.stream.flush();
            this.stream.close();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    private void writeHuffmanCodeInBits(final Character c) {
        final String encodedChar = converter.characterToHuffmanCode(c);

        this.stream.writeHuffmanCode(encodedChar);
    }

    private void writeNewLineSymbolInBits() {
        for (int i = 0; i < newLineSep.length(); i++) {
            final char c = this.newLineSep.charAt(i);
            writeHuffmanCodeInBits(c);
        }
    }

    /**
     * Return the number of empty bits in the final byte at EOF.
     * @return number of empty bits in final byte.
     */
    public int getExtraBits() {
        return stream.getEndExtraBits();
    }

}
