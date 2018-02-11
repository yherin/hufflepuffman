/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.js.huffman.io;

import java.io.File;

/**
 *
 * @author jack
 */
public class InputFileHandler extends FileHandler {

    private File file;

    @Override
    public boolean isReady() {

        if (!this.hasFileSet()) {
            return false;
        }
        if (!this.file.exists()) {
            return false;
        }
        if (!this.file.canRead()) {
            return false;
        }
        return true;
    }

    @Override
    public void setFile(String path) {
        this.file = new File(path);
    }

    @Override
    public File getFile() {
        return this.file;
    }

    @Override
    public boolean hasFileSet() {
        return this.file != null;
    }

}
