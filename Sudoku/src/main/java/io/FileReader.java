package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * File reader
 */
public class FileReader {
    /**
     * Path of the file
     */
    private String filepath;

    /**
     * Sets the file to open
     * @param filepath Path to file
     */
    public FileReader(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Gets the content of the file
     * @return Content of file
     */
    public String getFileContents() {
        String content;

        try {
            content = new String(Files.readAllBytes(Paths.get(filepath)));
        } catch (IOException e) {
            return "";
        }

        return content;
    }

    /**
     * Converts string to 2-dimensional sudoku grid
     * @param str String to convert
     * @return 2-dimensional array
     */
    public int[][] convertStringToSudokuGrid(String str) {
        if(str.length() < 81)
            return null;
        else {
            int[][] output = new int[9][9];

            int row = 0;
            int column = 0;

            for(int i = 0; i < str.length(); i++) {
                output[row][column] = str.charAt(i);
                column++;

                if((i + 1) % 9 == 0) {
                    row++;
                    column = 0;
                }
            }

            return output;
        }
    }
}
