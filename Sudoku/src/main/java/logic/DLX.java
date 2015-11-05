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

    /**
     * Uncovers one column/reverse constraint by header using DLX uncover method
     *
     * @param headerColumn Header to uncover
     */
    public void uncoverColumn(Header headerColumn) {
        Node node1 = headerColumn.getUp();
        Node node2 = null;

        while(!node1.equals(headerColumn)) {
            node2 = node1.getLeft();

            while(!node1.equals(node2)) {
                node2.getUp().setDown(node2);
                node2.getDown().setUp(node2);
                node2.getHeader().setSize(node2.getHeader().getSize() + 1);
                node2 = node2.getLeft();
            }

            node1 = node1.getUp();
        }

        node2.getLeft().setRight(node2);
        node2.getRight().setLeft(node2);
    }
}
