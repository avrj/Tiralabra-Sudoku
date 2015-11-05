package logic;

import domain.Header;
import domain.Node;

/**
 * Methods to implement the Dancing Links algorithm
 */
public class DLX {
    /**
     * Covers one column/removes constraint by header using DLX cover method
     *
     * @param headerColumn Header of column to cover
     */
    public void coverColumn(Header headerColumn) {
        headerColumn.getLeft().setRight(headerColumn.getRight());
        headerColumn.getRight().setLeft(headerColumn.getLeft());

        Node node1 = headerColumn.getDown();
        Node node2 = null;

        while(!node1.equals(headerColumn)) {
            node2 = node1.getRight();

            while(!node2.equals(node1)) {
                node2.getUp().setDown(node2.getDown());
                node2.getDown().setUp(node2.getUp());
                node2.getHeader().setSize(node2.getHeader().getSize() - 1);
                node2 = node2.getRight();
            }

            node1 = node1.getDown();
        }
    }

    /**
     * Uncovers one column/reverse constraint by header using DLX uncover method
     *
     * @param headerColumn Header of column to uncover
     */
    public void uncoverColumn(Header headerColumn) {
        Node node1 = headerColumn.getUp();
        Node node2 = null;

        while(!node1.equals(headerColumn)) {
            node2 = node1.getLeft();

            while(!node2.equals(node1)) {
                node2.getUp().setDown(node2);
                node2.getDown().setUp(node2);
                node2.getHeader().setSize(node2.getHeader().getSize() + 1);
                node2 = node2.getLeft();
            }

            node1 = node1.getUp();
        }

        headerColumn.getLeft().setRight(headerColumn);
        headerColumn.getRight().setLeft(headerColumn);
    }

    /**
     * Finds the column with smallest size
     *
     * @param rootHeader The root header
     * @return Column with smallest size
     */
    public Header findColumnWithSmallestSize(Header rootHeader) {
            int smallestSize = Integer.MAX_VALUE;

            Header columnWithSmallestSize = null;
            Header column = (Header) rootHeader.getRight();

            while (!column.equals(rootHeader)) {
                if (column.getSize() < smallestSize) {
                    smallestSize = column.getSize();
                    columnWithSmallestSize = column;
                }

                column = (Header) column.getRight();
            }

            return columnWithSmallestSize;
    }
}
