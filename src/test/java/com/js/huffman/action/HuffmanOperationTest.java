/*
 * Copyright (C) 2018 Your Organisation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.js.huffman.action;

import com.js.huffman.action.HuffmanCompression;
import com.js.huffman.action.HuffmanDecompression;
import com.js.huffman.ui.AppTest;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jack
 */
public class HuffmanOperationTest {

    String original = "src/test/plain/lorem";
    String compressed = original + ".huff";
    String decompressed = original + "_d";

    /**
     * Test of run method, of class HuffmanDecompression.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        File compressedFile = new File(compressed);
        File decompressedFile = new File(decompressed);
        compressedFile.delete();
        decompressedFile.delete();
        assertTrue(!compressedFile.exists());
        assertTrue(!decompressedFile.exists());
        new HuffmanCompression(original, compressed).doCompression();
        assertTrue(compressedFile.exists());
        new HuffmanDecompression(compressed, decompressed).doDecompression();
        assertTrue(decompressedFile.exists());
        try {
            FileUtils.contentEquals(new File(original), decompressedFile);
        } catch (IOException ex) {
            Logger.getLogger(HuffmanOperationTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("io exception");
        }

    }

}
