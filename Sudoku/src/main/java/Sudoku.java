import domain.ColumnNode;
import logic.DLX;
import logic.SudokuSolver;

public class Sudoku {
    public static void main(String[] args) {
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

        int grid[][] = sudoku.getGrid();

        for(int i = 0; i < grid[0].length; i++) {
            for(int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();

        sudoku.solve();

        grid = sudoku.getGrid();

        for(int i = 0; i < grid[0].length; i++) {
            for(int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }
    }
}
