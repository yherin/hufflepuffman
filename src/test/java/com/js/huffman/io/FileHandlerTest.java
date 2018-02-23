/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import com.js.huffman.io.InputFileHandler;
import com.js.huffman.io.OutputFileHandler;
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
public class FileHandlerTest {

    InputFileHandler inputFileHandler;
    OutputFileHandler outputFileHandler;

    public FileHandlerTest() {
    }

    @Test
    public void inputFileNotReadyWhenFileNotSet() {
        inputFileHandler = new InputFileHandler();
        assertFalse(inputFileHandler.isReady());

    }

    @Test
    public void inputFileHandlerNotReadyWhenFileDoesNotExist() {
        inputFileHandler = new InputFileHandler();
        inputFileHandler.setFile("~/thisfiledoesnotexistonmyfilesystem.eze");
        assertFalse(inputFileHandler.isReady());
    }

    @Test
    public void outputFileNotReadyWhenFileNotSet() {
        outputFileHandler = new OutputFileHandler();
        assertFalse(outputFileHandler.isReady());
        outputFileHandler.setFile("~/output.txt");
        assertTrue(outputFileHandler.isReady());
    }

}
