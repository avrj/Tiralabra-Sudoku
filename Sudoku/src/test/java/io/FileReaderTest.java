package io;

import io.FileReader;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class FileReaderTest {
    private String str;
    private FileReader fileReader;
    private int[][] parsedGrid;

    @Before
    public void setUp() {
        str = "012345678012- 34567801234567801234x567801a2345678012345g678012345678012345678012345678";

        fileReader = new FileReader("test");

        parsedGrid = fileReader.convertStringToSudokuGrid(str);
    }

    @Test
    public void parsedGridShouldHaveNineRows() {
        assertEquals(9, parsedGrid.length);
    }

    @Test
    public void parsedGridShouldHaveNineColumns() {
        assertEquals(9, parsedGrid[0].length);
    }

    @Test
    public void parsingFewerThan81CharsReturnsNull() {
        parsedGrid = fileReader.convertStringToSudokuGrid("");

        assertNull(parsedGrid);
    }
}
