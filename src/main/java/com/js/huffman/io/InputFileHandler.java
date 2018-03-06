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
