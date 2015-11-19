package logic;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by avrj on 18.11.2015.
 */
public class SudokuSolverTest {
    @Before
    public void setUp() {
    }

    @Test
    public void testSudokuSolving() {
        SudokuSolver sudoku = new SudokuSolver(new int[][]{
                {3, 6, 1, 8, 9, 5, 4, 7, 0},
                {9, 0, 2, 7, 3, 4, 8, 1, 6},
                {7, 4, 8, 1, 0, 2, 3, 9, 5},
                {5, 7, 3, 0, 2, 6, 1, 4, 8},
                {8, 2, 9, 5, 4, 1, 0, 3, 7},
                {0, 1, 6, 3, 8, 7, 5, 2, 9},
                {6, 9, 7, 4, 1, 8, 2, 5, 3},
                {1, 8, 5, 2, 7, 3, 9, 0, 4},
                {2, 3, 4, 6, 5, 9, 7, 8, 1}
        });

        sudoku.solve();

        String expected_output = "3 6 1 | 8 9 5 | 4 7 2 \n" +
                "9 5 2 | 7 3 4 | 8 1 6 \n" +
                "7 4 8 | 1 6 2 | 3 9 5 \n" +
                "---------------------\n" +
                "5 7 3 | 9 2 6 | 1 4 8 \n" +
                "8 2 9 | 5 4 1 | 6 3 7 \n" +
                "4 1 6 | 3 8 7 | 5 2 9 \n" +
                "---------------------\n" +
                "6 9 7 | 4 1 8 | 2 5 3 \n" +
                "1 8 5 | 2 7 3 | 9 6 4 \n" +
                "2 3 4 | 6 5 9 | 7 8 1 \n\n";

        assertEquals(expected_output, sudoku.print());
    }

    @Test
    public void testPrintFixedWidth() {
        SudokuSolver sudoku = new SudokuSolver(new int[][]{
                {3, 6, 1, 8, 9, 5, 4, 7, 0},
                {9, 0, 2, 7, 3, 4, 8, 1, 6},
                {7, 4, 8, 1, 0, 2, 3, 9, 5},
                {5, 7, 3, 0, 2, 6, 1, 4, 8},
                {8, 2, 9, 5, 4, 1, 0, 3, 7},
                {0, 1, 6, 3, 8, 7, 5, 2, 9},
                {6, 9, 7, 4, 1, 8, 2, 5, 3},
                {1, 8, 5, 2, 7, 3, 9, 0, 4},
                {2, 3, 4, 6, 5, 9, 7, 8, 1}
        });

        assertEquals("         0", sudoku.printFixedWidth("0", 10));
    }
}
