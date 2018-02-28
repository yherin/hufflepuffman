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

    public abstract void setFile(String absolutePath);

    public abstract File getFile();

    public abstract boolean hasFileSet();

    public abstract boolean isReady();

}
