package logic;

import domain.ColumnNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DLXTest {
    private DLX dlx;
    private int[][] dummyGrid;
    private byte[][] testMatrix;

    public DLXTest() {

    }

    @Before
    public void setUp() {
        dummyGrid = new int[][]{
                {0}
        };

        testMatrix = new byte[][]{
                {1, 0},
                {0, 0},
        };

        dlx = new DLX(dummyGrid);
    }

    @Test
    public void doublyLinkedListRootNodesRightColumnSizeIsOne() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        assertEquals(1, rootNode.getRight().getHeader().getSize());
    }

    @Test
    public void doublyLinkedListRootNodesRightRightColumnSizeIsZero() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        assertEquals(0, rootNode.getRight().getRight().getHeader().getSize());
    }


    @Test
    public void doublyLinkedListRootNodesLeftIsRootNodesRightsRightNode() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        assertEquals(rootNode.getLeft(), rootNode.getRight().getRight());
    }

    @Test
    public void doublyLinkedListRootNodesRightBottomNodeEqualsRootNodesRightColumnHeader() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        assertEquals(rootNode.getRight().getHeader(), rootNode.getRight().getDown().getDown());
    }

    @Test
    public void doublyLinkedListRootNodesRightRightBottomNodeEqualsRootNodesRightRightColumnHeader() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        assertEquals(rootNode.getRight().getRight().getHeader(), rootNode.getRight().getRight().getDown().getDown());
    }

    @Test
    public void afterCoveringRootNodesRightColumnTheNewRootNodesRightColumnIsTheRightOfTheCoveredColumn() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        ColumnNode rootNodesRightRightColumn = rootNode.getRight().getRight().getHeader();
        dlx.coverColumn(rootNode.getRight().getHeader());

        assertEquals(rootNodesRightRightColumn, rootNode.getRight().getHeader());

    }

    @Test
    public void afterCoveringRootNodesRightRightColumnTheNewRootNodesLeftColumnIsTheLeftOfTheCoveredColumn() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        ColumnNode rootNodesRightColumn = rootNode.getRight().getHeader();
        dlx.coverColumn(rootNode.getRight().getRight().getHeader());

        assertEquals(rootNodesRightColumn, rootNode.getLeft().getHeader());

    }

    @Test
    public void afterCoveringAndUncoveringRootNodesRightColumnTheRootNodesRightColumnisTheCoveredColumn() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        ColumnNode coveredColumn = rootNode.getRight().getHeader();

        dlx.coverColumn(coveredColumn);
        dlx.uncoverColumn(coveredColumn);

        assertEquals(coveredColumn, rootNode.getRight().getHeader());
    }

    @Test
    public void afterCoveringAndUncoveringRootNodesRightRightColumnTheRootNodesLeftColumnisTheCoveredColumn() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        ColumnNode coveredColumn = rootNode.getRight().getRight().getHeader();

        dlx.coverColumn(coveredColumn);
        dlx.uncoverColumn(coveredColumn);

        assertEquals(coveredColumn, rootNode.getLeft().getHeader());
    }


    @Test
    public void columnWithSmallestSizeIsFound() {
        ColumnNode rootNode = dlx.createDoublyLinkedLists(testMatrix);

        ColumnNode columnNode = dlx.findColumnWithSmallestSize();

        assertEquals(0, columnNode.getSize());
    }
}
