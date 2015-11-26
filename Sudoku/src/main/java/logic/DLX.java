package logic;

import domain.ColumnNode;
import domain.Node;

import java.util.*;

/**
 * Methods to implement the Dancing Links algorithm
 */
public class DLX {
    private int gridSize, gridN;
    private int[][] grid;

    /**
     * List of solutions
     */
    private ArrayList solutions = new ArrayList();

    /**
     * Root node is the starting point of the algorithm
     */
    private ColumnNode rootNode;


    /**
     * Initialize the algorithm with given grid
     *
     * @param grid Sudoku grid
     */
    public DLX(int[][] grid) {
        this.gridSize = grid[0].length / 3;
        this.gridN = grid[0].length;

        this.grid = grid;

        rootNode = null;
    }

    /**
     * Start of the algorithm
     *
     * @param initialMatrix Sudoku grid
     */
    public void run(int[][] initialMatrix) {
        byte[][] matrix = createMatrix(gridN, gridSize, initialMatrix);

        ColumnNode doublyLinkedList = createDoublyLinkedLists(matrix);

        search(0);
    }

    /**
     * Creates the sparse matrix from sudoku grid
     *
     * @param initialMatrix Sudoku grid
     * @return Sparse matrix
     */
    public byte[][] createMatrix(int gridN, int gridSize, int[][] initialMatrix) {
        int[][] clues = null;

        ArrayList cluesList = new ArrayList();

        int counter = 0;

        for (int row = 0; row < gridN; row++) {
            for (int column = 0; column < gridN; column++) {
                if (initialMatrix[row][column] > 0) {
                    cluesList.add(new int[]{initialMatrix[row][column], row, column});
                    counter++;
                }
            }
        }

        clues = new int[counter][];

        for (int i = 0; i < counter; i++) {
            clues[i] = (int[]) cluesList.get(i);
        }

        byte[][] matrix = new byte[gridN * gridN * gridN][4 * gridN * gridN];

        for (int d = 0; d < gridN; d++) {
            for (int r = 0; r < gridN; r++) {
                for (int c = 0; c < gridN; c++) {
                    if (!filled(gridN, gridSize, d, r, c, clues)) {
                        int rowIndex = c + (gridN * r) + (gridN * gridN * d);

                        int blockIndex = ((c / gridSize) + ((r / gridSize) * gridSize));
                        int colIndexRow = 3 * gridN * d + r;
                        int colIndexCol = 3 * gridN * d + gridN + c;
                        int colIndexBlock = 3 * gridN * d + 2 * gridN + blockIndex;
                        int colIndexSimple = 3 * gridN * gridN + (c + gridN * r);

                        matrix[rowIndex][colIndexRow] = 1;
                        matrix[rowIndex][colIndexCol] = 1;
                        matrix[rowIndex][colIndexBlock] = 1;
                        matrix[rowIndex][colIndexSimple] = 1;
                    }
                }
            }
        }
        return matrix;
    }

    /**
     * Checks if the cell to be filled is already filled with a digit
     *
     * @param digit   Digit
     * @param row     Row
     * @param col     Column
     * @param prefill Clues
     * @return True if already filled
     */
    public boolean filled(int gridN, int gridSize, int digit, int row, int col, int[][] prefill) {
        boolean filled = false;

        if (prefill != null) {
            for (int i = 0; i < prefill.length; i++) {
                int curDigit = prefill[i][0] - 1;
                int curRow = prefill[i][1];
                int curCol = prefill[i][2];

                int blockStartIndexCol = (curCol / gridSize) * gridSize;
                int blockEndIndexCol = blockStartIndexCol + gridSize;
                int blockStartIndexRow = (curRow / gridSize) * gridSize;
                int blockEndIndexRow = blockStartIndexRow + gridSize;

                if (curDigit != digit && row == curRow && col == curCol) {
                    filled = true;
                } else if ((curDigit == digit) && (row == curRow || col == curCol) && !(row == curRow && col == curCol)) {
                    filled = true;
                } else if ((curDigit == digit) && (row > blockStartIndexRow) && (row < blockEndIndexRow) && (col > blockStartIndexCol) && (col < blockEndIndexCol) && !(row == curRow && col == curCol)) {
                    filled = true;
                }
            }
        }
        return filled;
    }

    /**
     * Creates doubly linked list from sparse matrix
     *
     * @param matrix Sparse matrix
     * @return The root node of the list
     */
    public ColumnNode createDoublyLinkedLists(byte[][] matrix) {
        rootNode = new ColumnNode();

        ColumnNode curColumn = rootNode;

        for (int col = 0; col < matrix[0].length; col++) {
            curColumn.setRight(new ColumnNode());
            curColumn.getRight().setLeft(curColumn);
            curColumn = (ColumnNode) curColumn.getRight();

            if (col < 3 * gridN * gridN) {
                int digit = (col / (3 * gridN)) + 1;
                curColumn.setNumber(digit);

                int index = col - (digit - 1) * 3 * gridN;

                if (index < gridN) {
                    curColumn.setConstraint(0);
                    curColumn.setPosition(index);
                } else if (index < 2 * gridN) {
                    curColumn.setConstraint(1);
                    curColumn.setPosition(index - gridN);
                } else {
                    curColumn.setConstraint(2);
                    curColumn.setPosition(index - 2 * gridN);
                }
            } else {
                curColumn.setConstraint(3);
                curColumn.setPosition(col - 3 * gridN * gridN);
            }
            curColumn.setHead(curColumn);
        }

        curColumn.setRight(rootNode);

        rootNode.setLeft(curColumn);

        for (int row = 0; row < matrix.length; row++) {

            curColumn = (ColumnNode) rootNode.getRight();

            Node lastCreatedElement = null;
            Node firstElement = null;

            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    Node colElement = curColumn;

                    while (colElement.getDown() != null) {
                        colElement = colElement.getDown();
                    }

                    colElement.setDown(new Node());

                    if (firstElement == null) {
                        firstElement = colElement.getDown();
                    }

                    colElement.getDown().setUp(colElement);
                    colElement.getDown().setLeft(lastCreatedElement);
                    colElement.getDown().setHead(curColumn);

                    if (lastCreatedElement != null) {
                        colElement.getDown().getLeft().setRight(colElement.getDown());
                    }

                    lastCreatedElement = colElement.getDown();

                    curColumn.setSize(curColumn.getSize() + 1);
                }

                curColumn = (ColumnNode) curColumn.getRight();
            }

            if (lastCreatedElement != null) {
                lastCreatedElement.setRight(firstElement);
                firstElement.setLeft(lastCreatedElement);
            }
        }

        curColumn = (ColumnNode) rootNode.getRight();

        for (int i = 0; i < matrix[0].length; i++) {
            Node colElement = curColumn;

            while (colElement.getDown() != null) {
                colElement = colElement.getDown();
            }

            colElement.setDown(curColumn);
            curColumn.setUp(colElement);
            curColumn = (ColumnNode) curColumn.getRight();
        }

        return rootNode;
    }

    /**
     * Searching algorithm
     *
     * @param curDepth Depth
     */
    public void search(int curDepth) {
        if (rootNode.getRight() == rootNode) {
            mapSolvedToGrid();
            return;
        }

        ColumnNode c = findColumnWithSmallestSize();

        coverColumn(c);

        Node r = c.getDown();

        while (r != c) {
            if (curDepth < solutions.size()) {
                solutions.remove(curDepth);
            }

            solutions.add(curDepth, r);

            Node j = r.getRight();

            while (j != r) {
                coverColumn(j.getHeader());
                j = j.getRight();
            }

            search(curDepth + 1);

            Node r2 = (Node) solutions.get(curDepth);

            Node j2 = r2.getLeft();

            while (j2 != r2) {
                uncoverColumn(j2.getHeader());
                j2 = j2.getLeft();
            }

            r = r.getDown();
        }

        uncoverColumn(c);
    }

    /**
     * Maps the solved linked list to the grid
     */
    public void mapSolvedToGrid() {
        int[] result = new int[gridN * gridN];

        for (Iterator it = solutions.iterator(); it.hasNext(); ) {
            int number = -1;
            int cellNo = -1;

            Node element = (Node) it.next();

            Node next = element;

            do {
                if (next.getHeader().getConstraint() == 0) {
                    number = next.getHeader().getNumber();
                } else if (next.getHeader().getConstraint() == 3) {
                    cellNo = next.getHeader().getPosition();
                }

                next = next.getRight();
            } while (element != next);

            result[cellNo] = number;
        }

        int resultCounter = 0;

        for (int row = 0; row < gridN; row++) {
            for (int column = 0; column < gridN; column++) {
                grid[row][column] = result[resultCounter];
                resultCounter++;
            }
        }
    }

    /**
     * Covers one column/removes constraint by header using DLX cover method
     *
     * @param columnNode ColumnNode of column to cover
     */
    public void coverColumn(ColumnNode columnNode) {
        columnNode.getLeft().setRight(columnNode.getRight());
        columnNode.getRight().setLeft(columnNode.getLeft());

        Node curRow = columnNode.getDown();

        while (!curRow.equals(columnNode)) {
            Node curNode = curRow.getRight();

            while (!curNode.equals(curRow)) {
                curNode.getUp().setDown(curNode.getDown());
                curNode.getDown().setUp(curNode.getUp());
                curNode.getHeader().setSize(curNode.getHeader().getSize() - 1);
                curNode = curNode.getRight();
            }

            curRow = curRow.getDown();
        }
    }

    /**
     * Uncovers one column/reverse constraint by header using DLX uncover method
     *
     * @param columnNode ColumnNode of column to uncover
     */
    public void uncoverColumn(ColumnNode columnNode) {
        Node curRow = columnNode.getUp();

        while (!curRow.equals(columnNode)) {
            Node curNode = curRow.getLeft();

            while (!curNode.equals(curRow)) {
                curNode.getUp().setDown(curNode);
                curNode.getDown().setUp(curNode);
                curNode.getHeader().setSize(curNode.getHeader().getSize() + 1);
                curNode = curNode.getLeft();
            }

            curRow = curRow.getUp();
        }

        columnNode.getLeft().setRight(columnNode);
        columnNode.getRight().setLeft(columnNode);
    }

    /**
     * Finds the column with smallest set of nodes
     *
     * @return The column with least nodes
     */
    public ColumnNode findColumnWithSmallestSize() {
        ColumnNode rightOfRoot = (ColumnNode) rootNode.getRight();
        ColumnNode smallest = rightOfRoot;

        while (rightOfRoot.getRight() != rootNode) {
            rightOfRoot = (ColumnNode) rightOfRoot.getRight();

            if (rightOfRoot.getSize() < smallest.getSize()) {
                smallest = rightOfRoot;
            }
        }

        return smallest;
    }
}
