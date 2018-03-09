package com.js.huffman.io;

import java.io.File;

/**
 * File utility class that handles file status and File object creation.
 *
 * @author jack
 */
public class OutputFileHandler extends FileHandler {

    private File file;

    /**
     * Check if the file is in a ready state.
     * @return true if and only if this FileHandler has a file set.
     */
    @Override
    public boolean isReady() {

        if (!this.hasFileSet()) {
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
     * Get the file.
     * @return the file associated with this OutputFileHandler
     * @throws UnsupportedOperationException
     */
    @Override
    public File getFile() {
        if (hasFileSet()) {
            return this.file;
        } else {
            throw new UnsupportedOperationException("Failed to get file. No file was set.");
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
