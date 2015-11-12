package logic;

import domain.ColumnNode;
import domain.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DLXTest {
    private DLX dlx;
    private ColumnNode rootColumnNode, columnNodeLeft, columnNodeRight;
    private Node nodeOfHeaderLeft, nodeOfHeaderRight;

    public DLXTest() {

    }

    @Before
    public void setUp() {
        dlx = new DLX();

        initializeDLX();
    }

    private void initializeDLX() {
        rootColumnNode = new ColumnNode();
        columnNodeLeft = new ColumnNode();
        columnNodeRight = new ColumnNode();

        rootColumnNode.setRight(columnNodeLeft);
        rootColumnNode.setLeft(columnNodeRight);

        columnNodeLeft.setLeft(rootColumnNode);
        columnNodeLeft.setRight(columnNodeRight);
        columnNodeRight.setLeft(columnNodeLeft);
        columnNodeRight.setRight(rootColumnNode);

        nodeOfHeaderLeft = new Node(1);
        nodeOfHeaderLeft.setColumnNode(columnNodeLeft);
        columnNodeLeft.setSize(columnNodeLeft.getSize() + 1);
        nodeOfHeaderRight = new Node(1);
        nodeOfHeaderRight.setColumnNode(columnNodeRight);
        columnNodeRight.setSize(columnNodeRight.getSize() + 1);

        nodeOfHeaderLeft.setUp(columnNodeLeft);
        nodeOfHeaderRight.setUp(columnNodeRight);

        columnNodeLeft.setDown(nodeOfHeaderLeft);
        columnNodeRight.setDown(nodeOfHeaderRight);

        nodeOfHeaderLeft.setRight(nodeOfHeaderRight);
        nodeOfHeaderRight.setLeft(nodeOfHeaderLeft);

        nodeOfHeaderRight.setRight(nodeOfHeaderLeft);

        nodeOfHeaderLeft.setDown(columnNodeLeft);
        nodeOfHeaderRight.setDown(columnNodeRight);

        /* Commented out because causes endless loop
        columnNodeLeft.setUp(nodeOfHeaderLeft);
        columnNodeRight.setUp(nodeOfHeaderRight);*/
    }

    @Test
    public void coveringLeftColumnSetsRightColumnSizeToZero() {
        dlx.coverColumn(columnNodeLeft);

        assertEquals(0, columnNodeRight.getSize());
    }

    @Test
    public void coveringRightColumnSetsLeftColumnSizeToZero() {
        dlx.coverColumn(columnNodeRight);

        assertEquals(0, columnNodeLeft.getSize());
    }


    @Test
    public void coveringLeftColumnSetsRightColumnsLeftToRootColumn() {
        dlx.coverColumn(columnNodeLeft);

        assertEquals(rootColumnNode, columnNodeRight.getLeft());
    }

    @Test
    public void coveringLeftColumnSetsRootColumnsRightToRightColumn() {
        dlx.coverColumn(columnNodeLeft);

        assertEquals(columnNodeRight, rootColumnNode.getRight());
    }

    @Test
    public void uncoveringLeftColumnSetsRightColumnsLeftToLeftColumn() {
        dlx.coverColumn(columnNodeLeft);
        dlx.uncoverColumn(columnNodeLeft);

        assertEquals(columnNodeLeft, columnNodeRight.getLeft());
    }

    @Test
    public void uncoveringLeftColumnSetsRootColumnsRightToLeftColumn() {
        dlx.coverColumn(columnNodeLeft);
        dlx.uncoverColumn(columnNodeLeft);

        assertEquals(columnNodeLeft, rootColumnNode.getRight());
    }
}
