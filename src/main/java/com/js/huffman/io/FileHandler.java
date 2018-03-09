package com.js.huffman.io;

import java.io.File;

/**
 * Abstract file handling class.
 *
 * @see InputFileHandler
 * @see OutputFileHandler
 * @author jack
 */
public abstract class FileHandler {

    /**
     * Set the file used by this FileHandler.
     * @param absolutePath
     */
    public abstract void setFile(String absolutePath);

    /**
     * Get this FileHandler's File.
     * @return the file associated with this FileHandler.
     */
    public abstract File getFile();

    /**
     * Check if this FileHandler has a file set.
     * @return true if file is set, else false.
     */
    public abstract boolean hasFileSet();

    /**
     * Check if the file is ready for operation.
     * @return true if ready, else false.
     */
    public abstract boolean isReady();

}
