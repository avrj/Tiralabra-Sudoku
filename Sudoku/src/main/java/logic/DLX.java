package logic;

import domain.Header;
import domain.Node;

/**
 * Methods to implement the Dancing Links algorithm
 */
public class DLX {
    /**
     * Covers one column by header using DLX cover method
     *
     * @param headerColumn Header to cover
     */
    public void coverColumn(Header headerColumn) {
        headerColumn.getLeft().setRight(headerColumn.getRight());
        headerColumn.getRight().setLeft(headerColumn.getLeft());

        Node node1 = headerColumn.getDown();
        Node node2 = null;

        while(!node1.equals(headerColumn)) {
            node2 = node1.getRight();

            while(node1.equals(node2)) {
                node2.getUp().setDown(node2.getDown());
                node2.getDown().setUp(node2.getUp());
                node2.getHeader().setSize(node2.getHeader().getSize() - 1);
                node2 = node2.getRight();
            }

            node1 = node1.getDown();
        }
    }
}
