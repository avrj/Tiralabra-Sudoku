package logic;

import domain.ColumnNode;
import domain.Node;
import java.util.*;

/**
 * Methods to implement the Dancing Links algorithm
 */
public class DLX {
    /**
     * Stack of solutions
     */
    private Stack solutions;

    /**
     * Root node is the starting point of the algorithm
     */
    private ColumnNode rootNode;

    /**
     * An array of columns in matrix
     */
    private ArrayList<ColumnNode> columns;

    /**
     * An array of rows in matrix
     */
    private ArrayList<Node> rows;

    /**
     * Total count of solutions
     */
    private int totalSolutions;


    /**
     * Creates the root node and sets total solutions to zero
     */
    public DLX() {
        solutions = new Stack<>();
        columns = new ArrayList<>();
        rows = new ArrayList<>();

        rootNode = new ColumnNode(0);
        totalSolutions = 0;
    }

    /**
     * Builds the sparse matrix from array of 0's and 1's
     * @param input Two-dimensional array of 0's and 1's
     */
    public void buildMatrix(int[][] input) {
        int columnCount = input[0].length;
        int rowCount = input.length;

        buildSkeleton(rowCount, columnCount);

        for (int i = 0; i < input.length; i++) {
            appendRow(input[i], i);
        }
    }

    /**
     * Gets the solutions
     * @return An array of solutions
     */
    public ArrayList<Integer> getSolutions() {
        if (solutions.size() == 0) return null;

        ArrayList<Integer> finalSolutions = new ArrayList<>();

        Iterator iter = solutions.iterator();

        while (iter.hasNext()){
            finalSolutions.add(((Node) iter.next()).getRow());
        }

        return finalSolutions;
    }

    /**
     * Creates an empty matrix
     *
     * @param rowCount Row count
     * @param columnCount Column count
     */
    public void buildSkeleton(int rowCount, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            ColumnNode newColumnNode = new ColumnNode();
            newColumnNode.setLeft(rootNode.getLeft());
            newColumnNode.setRight(rootNode);
            newColumnNode.setRight(rootNode);

            rootNode.getLeft().setRight(newColumnNode);
            rootNode.setLeft(newColumnNode);

            columns.add(newColumnNode);
        }

        for (int i = 0; i < rowCount; i++) {
            rows.add(new Node());
        }
    }

    /**
     * Appends one row to the matrix
     *
     * @param rowInput Integer array representing the row (0's and 1's)
     * @param row The number of the row
     */
    public void appendRow(int[] rowInput, int row) {
        Node node1 = null, node2 = null;

        for (int i = 0; i < rowInput.length; i++) {
            if (rowInput[i] == 1) {
                if (node1 != null) {
                    node2 = new Node(row);

                    node2.setLeft(node1.getLeft());
                    node2.setRight(node1);
                    node1.getLeft().setRight(node2);
                    node1.setLeft(node2);

                    node2.setDown(columns.get(i));
                    node2.setUp(columns.get(i).getUp());

                    columns.get(i).getUp().setDown(node2);
                    columns.get(i).setUp(node2);

                    node2.setColumnNode(columns.get(i));
                    node2.getColumnNode().setSize(node2.getColumnNode().getSize() + 1);
                } else {
                    node1 = new Node(row);

                    node1.setColumnNode(columns.get(i));
                    node1.getColumnNode().setSize(node1.getColumnNode().getSize() + 1);
                    node1.setDown(columns.get(i));
                    node1.setUp(columns.get(i).getDown());

                    columns.get(i).getUp().setDown(node1);
                    columns.get(i).setUp(node1);

                    rows.set(row, node1);
                }
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

        Node node1 = columnNode.getDown();
        Node node2 = null;

        while(!node1.equals(columnNode)) {
            node2 = node1.getRight();

            while(!node2.equals(node1)) {
                node2.getUp().setDown(node2.getDown());
                node2.getDown().setUp(node2.getUp());
                node2.getColumnNode().setSize(node2.getColumnNode().getSize() - 1);
                node2 = node2.getRight();
            }

            node1 = node1.getDown();
        }
    }

    /**
     * Uncovers one column/reverse constraint by header using DLX uncover method
     *
     * @param columnNode ColumnNode of column to uncover
     */
    public void uncoverColumn(ColumnNode columnNode) {
        Node node1 = columnNode.getUp();
        Node node2 = null;

        while(!node1.equals(columnNode)) {
            node2 = node1.getLeft();

            while(!node2.equals(node1)) {
                node2.getUp().setDown(node2);
                node2.getDown().setUp(node2);
                node2.getColumnNode().setSize(node2.getColumnNode().getSize() + 1);
                node2 = node2.getLeft();
            }

            node1 = node1.getUp();
        }

        columnNode.getLeft().setRight(columnNode);
        columnNode.getRight().setLeft(columnNode);
    }

    /**
     * Selects one row to force solution
     *
     * @param index The index in rows[] of the row to select
     */
    public void selectRow(int index) {
        Node node = rows.get(index);

        solutions.push(node);

        do {
            coverColumn(node.getColumnNode());
            node = node.getRight();
        } while (node != rows.get(index));
    }

    /**
     * Unselects the last row pushed to the solutions stack
     */
    public void unselectRow() {
        Node node = ((Node) solutions.pop()).getLeft();

        do {
            uncoverColumn(node.getColumnNode());

            node = node.getLeft();
        } while (node != rows.get(node.getRow()));

        uncoverColumn(node.getColumnNode());
    }

    /**
     * Selects multiple rows
     * @param index Indexes in rows[] of the rows to select
     */
    public void selectMultipleRows(int[] index) {
        for (int i = 0; i < index.length; i++) {
            selectRow(index[i]);
        }
    }

    /**
     * Unselects all rows pushed to stack
     */
    public void unselectAllRows() {
        while (solutions.size() > 0) {
            unselectRow();
        }
    }

    /**
     * Solves the exact cover problem
     */
    public void solve() {
        solutions.clear();

        totalSolutions = 0;

        solveRecurse();
        unselectAllRows();
    }

    /**
     * Original Knuth's Dancing Links algorithm
     */
    public void solveRecurse() {
        if (rootNode.getRight() == rootNode) {
            totalSolutions++;
            return;
        }

        ColumnNode column = findColumnWithSmallestSize();

        if (column.getSize() == 0) return; // not a good solution

        coverColumn(column);

        Node node1 = column.getDown();
        Node node2 = null;

        while (node1 != column) {
            solutions.push(node1);

            node2 = node1.getRight();

            while (node2 != node1) {
                coverColumn(node2.getColumnNode());
                node2 = node2.getRight();
            }

            solveRecurse();

            node1 = (Node) solutions.pop();

            column = node1.getColumnNode();
            node2 = node1.getLeft();

            while (node2 != node1) {
                uncoverColumn(node2.getColumnNode());
                node2 = node2.getLeft();
            }

            node1 = node1.getDown();
        }
        
        uncoverColumn(column);
    }

    /**
     *  Finds the column with least nodes
     * @return Column header with least nodes
     */
    public ColumnNode findColumnWithSmallestSize() {
        int smallestSize = Integer.MAX_VALUE;

        ColumnNode columnWithSmallestSize = null;
        ColumnNode column = (ColumnNode) rootNode.getRight();

        while (!column.equals(rootNode)) {
            if (column.getSize() < smallestSize) {
                smallestSize = column.getSize();
                columnWithSmallestSize = column;
            }

            column = (ColumnNode) column.getRight();
        }

        return columnWithSmallestSize;
    }
}
