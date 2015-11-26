import logic.SudokuSolver;

public class Sudoku {
    public static void main(String[] args) {
        SudokuSolver veryEasySudoku = new SudokuSolver(new int[][]{
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

        SudokuSolver easySudoku = new SudokuSolver(new int[][]{
                {4, 5, 0, 0, 0, 0, 0, 3, 0},
                {7, 0, 0, 0, 0, 3, 0, 9, 1},
                {3, 0, 6, 0, 1, 0, 4, 5, 0},
                {9, 0, 0, 7, 0, 5, 0, 0, 6},
                {6, 0, 0, 1, 0, 9, 0, 0, 5},
                {8, 0, 0, 6, 0, 4, 0, 0, 9},
                {0, 6, 4, 0, 7, 0, 5, 0, 3},
                {2, 7, 0, 3, 0, 0, 0, 0, 4},
                {0, 3, 0, 0, 0, 0, 0, 7, 8}
        });

        SudokuSolver mediumSudoku = new SudokuSolver(new int[][]{
                {9, 1, 3, 6, 0, 0, 0, 0, 0},
                {2, 5, 0, 0, 8, 7, 0, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 4, 0},
                {5, 8, 0, 0, 4, 6, 0, 0, 7},
                {0, 0, 0, 0, 5, 0, 0, 0, 0},
                {7, 0, 0, 8, 9, 0, 0, 5, 6},
                {0, 9, 0, 0, 0, 0, 0, 0, 0},
                {0, 6, 0, 3, 7, 0, 0, 2, 9},
                {0, 0, 0, 0, 0, 5, 3, 6, 4}
        });

        SudokuSolver hardSudoku = new SudokuSolver(new int[][]{
                {4,0, 0,  2 ,0 ,7 , 0 ,5 ,0 },
                {0, 0, 3,  1 ,0 ,0 , 0 ,0 ,0 },
                {0, 2, 9 , 0 ,0 ,0  ,0 ,0 ,4 },
                {0, 0, 0,  0 ,0 ,0 , 0 ,4 ,2 },
                {2, 7, 0,  6 ,0 ,4 , 0 ,1 ,8 },
                {6, 4, 0 , 0 ,0 ,0,  0 ,0 ,0 },
                {1, 0, 0 , 0 ,0 ,0 , 2 ,8 ,0 },
                {0, 0, 0 , 0 ,0 ,1 , 4, 0 ,0 },
                {0, 8, 0 , 5, 0 ,6 , 0, 0 ,1 }
        });

        SudokuSolver veryHardSudoku = new SudokuSolver(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 6 ,2 ,0, 0, 9, 0},
                {0, 0, 2 ,0, 0, 9 , 3, 1, 0},
                {0, 0, 4 ,0, 0, 6, 0, 8, 0},
                {0, 0, 8 ,7, 0, 2 ,1, 0, 0},
                {0, 3, 0 ,8, 0, 0 , 5, 0, 0},
                {0, 6, 9, 1, 0, 0, 4, 0, 0},
                {0, 8 ,0, 0, 7 ,3 , 0, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        });



        int grid[][] = veryEasySudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        long timeAtStart = System.nanoTime();

        veryEasySudoku.solve();

        long timeAtEnd = System.nanoTime();

        System.out.println("Very easy: " + ((timeAtEnd - timeAtStart) / 1000000) + " ms");

        grid = veryEasySudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        grid = easySudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        timeAtStart = System.nanoTime();

        easySudoku.solve();

        timeAtEnd = System.nanoTime();

        System.out.println("Easy: " + ((timeAtEnd - timeAtStart) / 1000000) + " ms");

        grid = easySudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        grid = mediumSudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        timeAtStart = System.nanoTime();

        mediumSudoku.solve();

        timeAtEnd = System.nanoTime();

        System.out.println("Medium: " + ((timeAtEnd - timeAtStart) / 1000000) + " ms");

        grid = mediumSudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        grid = hardSudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        timeAtStart = System.nanoTime();

        hardSudoku.solve();

        timeAtEnd = System.nanoTime();

        System.out.println("Hard: " + ((timeAtEnd - timeAtStart) / 1000000) + " ms");

        grid = hardSudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        grid = veryHardSudoku.getGrid();

        /*for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/

        System.out.println();

        timeAtStart = System.nanoTime();

        veryHardSudoku.solve();

        timeAtEnd = System.nanoTime();

        System.out.println("Very hard: " + ((timeAtEnd - timeAtStart) / 1000000) + " ms");

        grid = veryHardSudoku.getGrid();

        /*
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }

            System.out.println();
        }*/
    }
}
