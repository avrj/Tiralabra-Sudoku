package domain;

/**
 * Represents the nodes of doubly linked list
 */
public class Node {
    /**
     * Left, right, up and down neighbours of this node
     */
    private Node left, right, up, down;

    /**
     * The header of this node
     */
    private Header header;

    /**
     * The row of this node
     */
    private int row;

    /**
     * Initializes node with row -1 and null header
     */
    public Node() {
        this(-1 , null);
    }

    /**
     * Initializes node with row
     * @param row The row of this node
     */
    public Node(int row) {
        this(row, null);
    }

    /**
     * Initializes node with row and header
     *
     * @param row The row of this node
     * @param header The header of this node
     */
    public Node(int row, Header header) {
        this.left = this;
        this.right = this;
        this.up = this;
        this.down = this;
        this.row = row;
        this.header = header;
    }

    /**
     * Sets the row
     *
     * @param row The row of this node
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Returns the header of this node
     *
     * @return Header of this node
     */
    public Header getHeader() {
        return this.header;
    }

    /**
     * Sets the header
     *
     * @param header The header of this node
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     * Returns the down neigbour of this node
     *
     * @return The down neighbour node
     */
    public Node getDown() {
        return down;
    }

    /**
     * Returns the right neighbour of this node
     *
     * @return The right neighbour node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the left neighbour
     *
     * @param left The left neighbour node
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Sets the right neighbour
     *
     * @param right The right neighbour node
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Returns the upper neighbour of this node
     *
     * @return The upper neighbour node
     */
    public Node getUp() {
        return up;
    }

    /**
     * Sets the down neighbour
     *
     * @param down The down neighbour node
     */
    public void setDown(Node down) {
        this.down = down;
    }

    /**
     * Sets the upper neighbour node
     *
     * @param up The upper neighbour node
     */
    public void setUp(Node up) {
        this.up = up;
    }

    /**
     * Sets the left neighbour node
     *
     * @return The left neighbour node
     */
    public Node getLeft() {
        return left;
    }
}
