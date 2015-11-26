package logic;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class SudokuCheckerTest {
    private SudokuChecker checker;

    @Before
    public void setUp() {
        checker = new SudokuChecker();
    }

    @Test
    public void validSudokuIsAccepted() {
        int[][] grid = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        assertTrue(checker.checkSolution(grid));
    }

    @Test
    public void invalidSudokuIsNotAccepted() {
        int[][] grid = new int[][] {
                {1, 2, 3},
                {4, 4, 6},
                {7, 8, 9}
        };

        assertFalse(checker.checkSolution(grid));
    }

    @Test
    public void unifinishedSudokuIsNotAccepted() {
        int[][] grid = new int[][] {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 9}
        };

        assertFalse(checker.checkSolution(grid));
    }
}
