package com.js.huffman;

import com.js.huffman.io.HuffmanCompression;
import com.js.huffman.io.HuffmanDecompression;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {

    Runner r = new Runner();

    @FXML
    private Label label;

    @FXML
    private TextField compressionInputFilePath;

    @FXML
    private TextField decompressionInputFilePath;

    @FXML
    private Label warnFileExtension;

    @FXML
    private Button btnMenuCompress;

    @FXML
    private Button btnMenuDecompress;

    @FXML
    private void compress() {
        final String input = compressionInputFilePath.getText();
        final String output = input + ".huff";
        new HuffmanCompression(input, output).run();
    }

    @FXML
    private void decompress() {
        final String input = decompressionInputFilePath.getText();
        if (input.endsWith(".huff")) {
            warnFileExtension(false);
            final String output = formatOutputFilename();
            new HuffmanDecompression(input, output).run();
        } else {
            warnFileExtension(true);
        }

    }

    private String formatOutputFilename() {
        String outputRaw = decompressionInputFilePath.getText().trim();
        StringBuilder sb = new StringBuilder(outputRaw );
        
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
    private void warnFileExtension(boolean warn) {
        this.warnFileExtension.setVisible(warn);
    }

    @FXML
    private void handleCompressionMenuButton(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
