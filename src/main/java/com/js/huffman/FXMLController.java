package com.js.huffman;

import com.js.huffman.io.HuffmanCompression;
import com.js.huffman.io.HuffmanDecompression;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    //Runner r = new Runner();
    @FXML
    private Label label;

    @FXML
    private TextField compressionInputFilePath;

    @FXML
    private TextField decompressionInputFilePath;

    @FXML
    private Label warnFileExtension;

    @FXML
    private Label success;

    @FXML
    private Button btnMenuCompress;

    @FXML
    private Button btnMenuDecompress;

    @FXML
    private Label warnIOError;

    /**
     * The entry point for file compression. Starts on a new thread.
     */
    @FXML
    private void compress() {
        try {
            final String input = compressionInputFilePath.getText();
            final String output = input + ".huff";
            new HuffmanCompression(input, output).run();
            this.showSuccess(true);
        } catch (UnsupportedOperationException e) {
            this.warnIOError(true);
        }
    }

    /**
     * The entry point for file decompression. Starts on a new thread.
     */
    @FXML
    private void decompress() {
        final String input = decompressionInputFilePath.getText();
        if (input.endsWith(".huff")) {
            warnFileExtension(false);
            try {
                final String output = formatOutputFilename();
                new HuffmanDecompression(input, output).run();
                this.showSuccess(true);
            } catch (UnsupportedOperationException e) {
                this.warnIOError(true);
            }
        } else {
            warnFileExtension(true);
        }

    }

    private String formatOutputFilename() {
        String outputRaw = decompressionInputFilePath.getText().trim();
        StringBuilder sb = new StringBuilder(outputRaw);

        sb.delete(sb.length() - 5, sb.length()); //remove .huff
        final int fileExtensionDot = sb.lastIndexOf(".");
        if (fileExtensionDot == -1) {
            sb.append("_decomp");
            return sb.toString();
        } else {
            sb.insert(fileExtensionDot, "-decomp");
            return sb.toString();
        }
    }

    @FXML
    private void warnIOError(boolean warn) {
        this.warnFileExtension.setVisible(false);
        this.success.setVisible(false);
        this.warnIOError.setVisible(warn);
    }

    @FXML
    private void showSuccess(boolean show) {
        this.warnFileExtension.setVisible(false);
        this.warnIOError.setVisible(false);
        this.success.setVisible(
                true);
    }

    @FXML
    private void warnFileExtension(boolean warn) {
        this.warnIOError.setVisible(false);
        this.success.setVisible(false);
        this.warnFileExtension.setVisible(warn);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
