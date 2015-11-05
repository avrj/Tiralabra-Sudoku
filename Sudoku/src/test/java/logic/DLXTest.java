package logic;

import domain.Header;
import domain.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DLXTest {
    private DLX dlx;
    private Header rootHeader, headerLeft, headerRight;
    private Node nodeOfHeaderLeft, nodeOfHeaderRight;

    public DLXTest() {

    }

    @Before
    public void setUp() {
        dlx = new DLX();

        initializeDLX();
    }

    private void initializeDLX() {
        rootHeader = new Header();
        headerLeft = new Header();
        headerRight = new Header();

        rootHeader.setRight(headerLeft);
        rootHeader.setLeft(headerRight);

        headerLeft.setLeft(rootHeader);
        headerLeft.setRight(headerRight);
        headerRight.setLeft(headerLeft);
        headerRight.setRight(rootHeader);

        nodeOfHeaderLeft = new Node(1);
        nodeOfHeaderLeft.setHeader(headerLeft);
        headerLeft.setSize(headerLeft.getSize() + 1);
        nodeOfHeaderRight = new Node(1);
        nodeOfHeaderRight.setHeader(headerRight);
        headerRight.setSize(headerRight.getSize() + 1);

        nodeOfHeaderLeft.setUp(headerLeft);
        nodeOfHeaderRight.setUp(headerRight);

        headerLeft.setDown(nodeOfHeaderLeft);
        headerRight.setDown(nodeOfHeaderRight);

        nodeOfHeaderLeft.setRight(nodeOfHeaderRight);
        nodeOfHeaderRight.setLeft(nodeOfHeaderLeft);

        nodeOfHeaderRight.setRight(nodeOfHeaderLeft);

        nodeOfHeaderLeft.setDown(headerLeft);
        nodeOfHeaderRight.setDown(headerRight);

        /* Commented out because causes endless loop
        headerLeft.setUp(nodeOfHeaderLeft);
        headerRight.setUp(nodeOfHeaderRight);*/
    }

    @Test
    public void coveringLeftColumnSetsRightColumnSizeToZero() {
        dlx.coverColumn(headerLeft);

        assertEquals(0, headerRight.getSize());
    }

    @Test
    public void coveringRightColumnSetsLeftColumnSizeToZero() {
        dlx.coverColumn(headerRight);

        assertEquals(0, headerLeft.getSize());
    }


    @Test
    public void coveringLeftColumnSetsRightColumnsLeftToRootColumn() {
        dlx.coverColumn(headerLeft);

        assertEquals(rootHeader, headerRight.getLeft());
    }

    @Test
    public void coveringLeftColumnSetsRootColumnsRightToRightColumn() {
        dlx.coverColumn(headerLeft);

        assertEquals(headerRight, rootHeader.getRight());
    }

    @Test
    public void uncoveringLeftColumnSetsRightColumnsLeftToLeftColumn() {
        dlx.coverColumn(headerLeft);
        dlx.uncoverColumn(headerLeft);

        assertEquals(headerLeft, headerRight.getLeft());
    }

    @Test
    public void uncoveringLeftColumnSetsRootColumnsRightToLeftColumn() {
        dlx.coverColumn(headerLeft);
        dlx.uncoverColumn(headerLeft);

        assertEquals(headerLeft, rootHeader.getRight());
    }

    @Test
    public void headerWithSmallestSizeIsReturned() {
        dlx.coverColumn(headerLeft);

        assertEquals(headerRight, dlx.findColumnWithSmallestSize(rootHeader));
    }
}
