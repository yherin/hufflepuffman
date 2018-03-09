package com.js.huffman.io;

import java.io.File;

/**
 *
 * @author jack
 */
public class InputFileHandler extends FileHandler {

    private File file;

    /**
     * Check if the file is in a ready state.
     * @return true if and only if this FileHandler has a file set, the set file
     * exists and the set file can be read. else false.
     */
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

    /**
     * Set the input file.
     * @param path
     */
    @Override
    public void setFile(String path) {
        this.file = new File(path);
    }

    /**
     * Get the input file.
     * @return the currently set input file.
     */
    @Override
    public File getFile() {
        if (hasFileSet()){
        return this.file;
        } else {
            throw new UnsupportedOperationException("Failed to set file.");
        }
    }

    /**
     * Check if this FileHandler has a file set.
     * @return true is this FileHandler's file is not null, else false;
     */
    @Override
    public boolean hasFileSet() {
        return this.file != null;
    }

}
