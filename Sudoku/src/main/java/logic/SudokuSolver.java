package logic;

/**
 * This class calls the DLX algorithm and takes care of printing the result
 */
public class SudokuSolver {
    /**
     * Size of the sudoku (normally 3)
     */
    private int SIZE;

    /**
     * The square of the size (normally 9)
     */
    private int N;

    /**
     * Sudoku grid
     */
    private int Grid[][];

    /**
     * Calls the solving method of DLX algorithm
     */
    public void solve() {
        DLX solver = new DLX(Grid);
        solver.run(Grid);
    }

    /**
     * Initializes the solver with sudoku grid and sets the size and size square parameters
     * @param gridi Sudoku grid
     */
    public SudokuSolver (int[][] gridi) {
        SIZE = gridi[0].length / 3;
        N = gridi[0].length;

        Grid = gridi;
    }

    /**
     * Returns the sudoku grid
     * @return Grid
     */
    public int[][] getGrid() {
        return Grid;
    }

    /**
     * Prints text with given amount of spaces
     * @param text  The text to print
     * @param width The total width of the text and spaces
     * @return  Fixed width text
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
     * @return Human readable format of the grid array
     */
    public String print() {
        int digits = (int) Math.floor(Math.log(N) / Math.log(10)) + 1;

        int lineLength = (digits + 1) * N + 2 * SIZE - 3;

        StringBuffer line = new StringBuffer();

        for (int lineInit = 0; lineInit < lineLength; lineInit++) {
            line.append('-');
        }


        String output = "";

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                output += printFixedWidth(String.valueOf(Grid[i][j]), digits);

                if ((j < N - 1) && ((j + 1) % SIZE == 0)) {
                    output += " |";
                }

                output += " ";
            }

            output += "\r\n";

            if ((i < N - 1) && ((i + 1) % SIZE == 0)) {
                output += line.toString() + "\r\n";
            }
        }

        return output;
    }
}