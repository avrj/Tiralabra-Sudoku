package gui;

import io.FileReader;
import logic.SudokuChecker;
import logic.SudokuSolver;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

/**
 * UI for sudoku
 */
public class GUI {
    private static JLabel checkSolution;
    private static JTextField[][] textFields;
    private static JButton solveSudoku;

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Sudoku");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private static void addComponentsToPane(Container pane) {
        BoxLayout boxLayout = new BoxLayout(pane, BoxLayout.Y_AXIS);

        pane.setLayout(boxLayout);

        pane.add(getButtonPanel());
        pane.add(getGridPanel());
    }

    private static void setGameStatus(boolean status) {
        if(!status) {
            checkSolution.setText("Unfinished");
            checkSolution.setForeground(Color.RED);
        } else {
            for(int column = 0; column < 9; column++) {
                for(int row = 0; row < 9; row++) {
                    textFields[row][column].setEnabled(false);
                }
            }

            checkSolution.setText("Solved");
            checkSolution.setForeground(Color.GREEN);
        }
    }

    private static void setGameAsSolved(long msecs) {
            for(int column = 0; column < 9; column++) {
                for(int row = 0; row < 9; row++) {
                    textFields[row][column].setEnabled(false);
                }
            }

            checkSolution.setText("Solved in " + msecs + " ms");
            checkSolution.setForeground(Color.GREEN);
    }

    private static void restoreEmptyCells() {
        for(int column = 0; column < 9; column++) {
            for(int row = 0; row < 9; row++) {
                if(textFields[row][column].isEnabled())
                    textFields[row][column].setText("");
            }
        }
    }

    private static void convertGridToTextfields(int[][] grid) {
        for(int column = 0; column < grid[0].length; column++) {
            for(int row = 0; row < grid.length; row++) {
                if(grid[row][column] == 0) {
                    textFields[column][row].setText("");
                    textFields[column][row].setEnabled(true);
                } else {
                    textFields[column][row].setText(Integer.toString(grid[row][column]));
                    textFields[column][row].setEnabled(false);
                }
            }
        }
    }

    private static int[][] convertTextfieldsToGrid() {
        int[][] output = new int[9][9];

        for(int column = 0; column < 9; column++) {
            for(int row = 0; row < 9; row++) {
                if(textFields[column][row].getText().isEmpty())
                    output[row][column] = 0;
                else
                    output[row][column] = Integer.parseInt(textFields[column][row].getText());
            }
        }

        return output;
    }

    private static JComponent getButtonPanel() {
        JPanel inner = new JPanel();

        inner.setLayout(new GridLayout(1, 3));

        JButton newSudokuFromFile = new JButton("Load sudoku from file...");

        checkSolution = new JLabel("", SwingConstants.CENTER);

        setGameStatus(false);

        solveSudoku = new JButton("Solve");
        solveSudoku.setEnabled(false);

        newSudokuFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                int result = fileChooser.showOpenDialog(inner);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Selected file: " + selectedFile.getAbsolutePath());

                    FileReader fileReader = new FileReader(selectedFile.getAbsolutePath());

                    int[][] parsedGrid = fileReader.convertStringToSudokuGrid(fileReader.getFileContents());

                    JOptionPane.showMessageDialog(null, "Loaded successfully " + parsedGrid[0].length + "x" + parsedGrid.length + " sudoku grid");
                    solveSudoku.setEnabled(true);
                    setGameStatus(false);

                    if(parsedGrid != null) convertGridToTextfields(parsedGrid);
                }
            }
        });

        solveSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restoreEmptyCells();
                int[][] grid = convertTextfieldsToGrid();

                SudokuSolver sudokuSolver = new SudokuSolver(grid);

                long timeAtStart = System.nanoTime();

                sudokuSolver.solve();

                long timeAtEnd = System.nanoTime();

                long msecs = ((timeAtEnd - timeAtStart) / 1000000);

                convertGridToTextfields(sudokuSolver.getGrid());

                solveSudoku.setEnabled(false);

                setGameAsSolved(msecs);
            }
        });

        inner.add(newSudokuFromFile);
        inner.add(checkSolution);
        inner.add(solveSudoku);

        return inner;
    }

    private static JComponent getGridPanel() {
        JPanel inner = new JPanel();

        inner.setLayout(new GridLayout(9, 9));

        textFields = new JTextField[9][9];

        for(int column = 0; column < 9; column++) {
            for(int row = 0; row < 9; row++) {
                textFields[row][column] = new JTextField();
                textFields[row][column].setEnabled(false);

                textFields[row][column].setHorizontalAlignment(JTextField.CENTER);

                final int newRow = row;
                final int newColumn = column;

                textFields[row][column].addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        checkInput();
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        checkInput();
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        checkInput();
                    }

                    private void checkInput() {
                        int[][] grid = convertTextfieldsToGrid();

                        SudokuChecker sudokuChecker = new SudokuChecker();

                        if(sudokuChecker.checkSolution(grid)) {
                            solveSudoku.setEnabled(false);
                            setGameStatus(true);
                        }

                        int x;
                        try {
                            x = Integer.parseInt(textFields[newRow][newColumn].getText());

                            if(x < 0) {
                                textFields[newRow][newColumn].setText(Integer.toString(Math.abs(x)));
                            } else if(x == 0) {
                                textFields[newRow][newColumn].setText("");
                            } else if(x > 9) {
                                try {
                                    textFields[newRow][newColumn].setText(textFields[newRow][newColumn].getText(0, 1));
                                } catch(BadLocationException e) {
                                    textFields[newRow][newColumn].setText("");
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            textFields[newRow][newColumn].setText("");
                        }
                    }
                });

                inner.add(textFields[row][column]);
            }
        }

        return inner;
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
