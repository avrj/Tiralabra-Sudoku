package logic;

import domain.Header;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DLXTest {
    private DLX dlx;
    private Header rootHeader, headerLeft, headerRight;

    public DLXTest() {

    }

    @Before
    public void setUp() {
        rootHeader = new Header();
        headerLeft = new Header();
        headerRight = new Header();

        rootHeader.setRight(headerLeft);

        headerLeft.setLeft(rootHeader);
        headerLeft.setRight(headerRight);
        headerRight.setLeft(headerLeft);

        dlx = new DLX();
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
}
