package logic;

/**
 * This class calls the DLX algorithm and takes care of printing the result
 */
public class SudokuSolver {
    /**
     * Size of the sudoku (normally 3)
     */
    private int size;

    /**
     * The square of the size (normally 9)
     */
    private int sizeSquare;

    /**
     * Sudoku grid
     */
    private int grid[][];

    /**
     * Calls the solving method of DLX algorithm
     */
    public void solve() {
        DLX solver = new DLX(grid);

        solver.run(grid);
    }

    /**
     * Initializes the solver with sudoku grid and sets the size and size square parameters
     * @param grid Sudoku grid
     */
    public SudokuSolver(int[][] grid) {
        size = grid[0].length / 3;
        sizeSquare = grid[0].length;

        this.grid = grid;
    }

    /**
     * Returns the sudoku grid
     *
     * @return Grid
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Prints text with given amount of spaces
     *
     * @param text  The text to print
     * @param width The total width of the text and spaces
     * @return Fixed width text
     */
    public String printFixedWidth(String text, int width) {
        String spaces = "";

        for (int i = 0; i < width - text.length(); i++) {
            spaces += " ";
        }


        return spaces + text;
    }

    /**
     * Prints the grid in human readable format
     *
     * @return Human readable format of the grid array
     */
    public String print() {
        int digits = (int) Math.floor(Math.log(sizeSquare) / Math.log(10)) + 1;

        int lineLength = (digits + 1) * sizeSquare + 2 * size - 3;

        StringBuffer line = new StringBuffer();

        for (int lineInit = 0; lineInit < lineLength; lineInit++) {
            line.append('-');
        }


        String output = "";

        for (int i = 0; i < sizeSquare; i++) {
            for (int j = 0; j < sizeSquare; j++) {
                output += printFixedWidth(String.valueOf(grid[i][j]), digits);

                if ((j < sizeSquare - 1) && ((j + 1) % size == 0)) {
                    output += " |";
                }

                output += " ";
            }

            output += "\r\n";

            if ((i < sizeSquare - 1) && ((i + 1) % size == 0)) {
                output += line.toString() + "\r\n";
            }
        }

        return output;
    }
}