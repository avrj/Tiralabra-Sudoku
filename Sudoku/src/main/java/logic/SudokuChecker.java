package logic;

/**
 * A class for determining if sudoku is incomplete
 */
public class SudokuChecker {

    /**
     * Checks if the given sudoku grid is incomplete
     *
     * @param grid 2-dimensional sudoku grid
     * @return false if incomplete
     */
    public boolean checkSolution(int[][] grid) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] columns = new boolean[9][10];
        boolean[][] squares = new boolean[9][10];

        boolean invalid = false;

        outerLoop:
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                if (grid[row][col] == 0) {
                    invalid = true;

                    break outerLoop;
                }

                int square = 0;

                if (row >= 0 && row <= 2) {
                    if (col >= 0 && col <= 2) {
                        square = 0;
                    } else if (col >= 2 && col <= 5) {
                        square = 1;
                    } else if (col >= 6 && col <= 8) {
                        square = 2;
                    }
                } else if (row >= 3 && row <= 5) {
                    if (col >= 0 && col <= 2) {
                        square = 3;
                    } else if (col >= 2 && col <= 5) {
                        square = 4;
                    } else if (col >= 6 && col <= 8) {
                        square = 5;
                    }
                } else if (row >= 6 && row <= 8) {
                    if (col >= 0 && col <= 2) {
                        square = 6;
                    } else if (col >= 2 && col <= 5) {
                        square = 7;
                    } else if (col >= 6 && col <= 8) {
                        square = 8;
                    }
                }

                if (rows[row][grid[row][col]] == true || columns[col][grid[row][col]] == true || squares[square][grid[row][col]] == true) {
                    invalid = true;

                    break outerLoop;
                } else {
                    rows[row][grid[row][col]] = true;
                    columns[col][grid[row][col]] = true;
                    squares[square][grid[row][col]] = true;
                }
            }
        }

        return !invalid;
    }
}
