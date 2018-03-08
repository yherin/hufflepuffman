/*
 * Copyright (C) 2018 Your Organisation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.js.huffman.ui;

import com.js.huffman.MainApp;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Jack
 */
public class AppTest extends ApplicationTest {

    MainApp app;
    String original = "src/test/plain/lorem";
    String compressed = original + ".huff";
    String decompressed = original + "_d";

    @Override
    public void start(Stage stage) {
        app = new MainApp();
        try {
            app.start(stage);
        } catch (Exception ex) {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void applicationFlowTest() throws InterruptedException {
        clickOn("#compressionInputFilePath");
        write(original);
        clickOn("#btnMenuCompress");
        sleep(500l);
        File compressedFile = new File(compressed);
        assertTrue(compressedFile.exists());
        clickOn("#decompressionInputFilePath");
        write(compressed);
        clickOn("#btnMenuDecompress");
        sleep(500l);
        File decompressedFile = new File(decompressed);
        assertTrue(decompressedFile.exists());
        try {
            FileUtils.contentEquals(new File(original), decompressedFile);
        } catch (IOException ex) {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("io exception");
        }
    }

}
