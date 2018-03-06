package com.js.huffman.io;

import java.io.File;

/**
 * File utility class that handles file status and File object creation.
 * @author jack
 */
public class OutputFileHandler extends FileHandler {

    private File file;

    @Override
    public boolean isReady() {

        if (!this.hasFileSet()) {
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
