package logic;

import domain.ColumnNode;
import domain.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DLXTest {
    private DLX dlx;
    private int[][] sudokuGrid;

    public DLXTest() {

    }

    @Before
    public void setUp() {
        sudokuGrid = new int[][]{
                {3, 6, 1, 8, 9, 5, 4, 7, 0},
                {9, 0, 2, 7, 3, 4, 8, 1, 6},
                {7, 4, 8, 1, 0, 2, 3, 9, 5},
                {5, 7, 3, 0, 2, 6, 1, 4, 8},
                {8, 2, 9, 5, 4, 1, 0, 3, 7},
                {0, 1, 6, 3, 8, 7, 5, 2, 9},
                {6, 9, 7, 4, 1, 8, 2, 5, 3},
                {1, 8, 5, 2, 7, 3, 9, 0, 4},
                {2, 3, 4, 6, 5, 9, 7, 8, 1}
        };

        dlx = new DLX(sudokuGrid);
    }

    @Test
    public void testDoubleLinkedList() {
        byte[][] matrix = new byte[][]{
                {1, 0, 1},
                {0, 1, 0},
                {0, 1, 0}
        };

        ColumnNode rootNode = dlx.createDoubleLinkedLists(matrix);

        assertEquals(rootNode.getLeft(), rootNode.getRight().getRight().getRight());

        assertEquals(1, rootNode.getRight().getHeader().getSize());
        assertEquals(2, rootNode.getRight().getRight().getHeader().getSize());
        assertEquals(1, rootNode.getRight().getRight().getRight().getHeader().getSize());

        assertEquals(rootNode.getRight(), rootNode.getRight().getHeader());
        assertEquals(rootNode.getRight().getRight(), rootNode.getRight().getRight().getHeader());
        assertEquals(rootNode.getRight().getRight().getRight(), rootNode.getRight().getRight().getRight().getHeader());
    }

    @Test
    public void testCoverColumn() {
        byte[][] matrix = new byte[][]{
                {1, 0, 1},
                {0, 1, 0},
                {0, 1, 0}
        };

        ColumnNode rootNode = dlx.createDoubleLinkedLists(matrix);

        assertEquals(1, rootNode.getRight().getHeader().getSize());

        ColumnNode coveredColumn = rootNode.getRight().getHeader();
        dlx.coverColumn(rootNode.getRight().getHeader());

        assertEquals(2, rootNode.getRight().getHeader().getSize());


        assertEquals(0, rootNode.getRight().getRight().getHeader().getSize());

        dlx.uncoverColumn(coveredColumn);

        assertEquals(1, rootNode.getRight().getHeader().getSize());
    }

    @Test
    public void testUncoverColumn() {
        byte[][] matrix = new byte[][]{
                {1, 0, 1},
                {0, 1, 0},
                {0, 1, 0}
        };

        ColumnNode rootNode = dlx.createDoubleLinkedLists(matrix);

        assertEquals(1, rootNode.getRight().getHeader().getSize());

        ColumnNode coveredColumn = rootNode.getRight().getHeader();
        dlx.coverColumn(rootNode.getRight().getHeader());

        assertEquals(2, rootNode.getRight().getHeader().getSize());


        assertEquals(0, rootNode.getRight().getRight().getHeader().getSize());

        dlx.uncoverColumn(coveredColumn);

        assertEquals(1, rootNode.getRight().getHeader().getSize());
    }

    @Test
    public void testColumnWithSmallestSizeIsFound() {
        byte[][] matrix = new byte[][]{
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 1}
        };

        ColumnNode rootNode = dlx.createDoubleLinkedLists(matrix);

        ColumnNode columnNode = dlx.findColumnWithSmallestSize();

        assertEquals(1, columnNode.getSize());
    }
}
