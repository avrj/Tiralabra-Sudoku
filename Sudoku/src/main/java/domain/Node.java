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
     * The columnNode of this node
     */
    private ColumnNode columnNode;

    /**
     * The row of this node
     */
    private int row;

    /**
     * Initializes node with row -1 and null columnNode
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
     * Initializes node with row and columnNode
     *
     * @param row The row of this node
     * @param columnNode The columnNode of this node
     */
    public Node(int row, ColumnNode columnNode) {
        this.left = this;
        this.right = this;
        this.up = this;
        this.down = this;
        this.row = row;
        this.columnNode = columnNode;
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
     * Returns the columnNode of this node
     *
     * @return ColumnNode of this node
     */
    public ColumnNode getColumnNode() {
        return this.columnNode;
    }

    /**
     * Sets the columnNode
     *
     * @param columnNode The columnNode of this node
     */
    public void setColumnNode(ColumnNode columnNode) {
        this.columnNode = columnNode;
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

    public int getRow() {
        return row;
    }
}
