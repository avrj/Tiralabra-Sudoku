package gui;

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

    private static JComponent getButtonPanel() {
        JPanel inner = new JPanel();

        inner.setLayout(new GridLayout(1, 3));

        JButton newSudokuFromFile = new JButton("Load sudoku from file...");
        JButton checkSolution = new JButton("Check validity");
        JButton solveSudoku = new JButton("Solve");

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
                    JOptionPane.showMessageDialog(null, fileReader.getFileContents());
                }
            }
        });

        checkSolution.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Väärä ratkaisu.");
            }
        });

        solveSudoku.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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

        for(int i = 1; i <= 9*9; i++) {
            JTextField testi = new JTextField();
            testi.setHorizontalAlignment(JTextField.CENTER);
            testi.setText(Integer.toString(new Random().nextInt(9) + 1));
            if(i % 2 == 0) {
                testi.setEnabled(false);
            }
            testi.addKeyListener(new KeyListener() {
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
                    int x;
                    try {
                        x = Integer.parseInt(testi.getText());

                        if(x < 0) {
                            testi.setText(Integer.toString(Math.abs(x)));
                        } else if(x > 9) {
                            try {
                                testi.setText(testi.getText(0, 1));
                            } catch(BadLocationException e) {
                                testi.setText("");
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        testi.setText("");
                    }
                }
            });
            inner.add(testi);
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
