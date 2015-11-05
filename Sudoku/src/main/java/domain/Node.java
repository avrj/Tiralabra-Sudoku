package domain;

/**
 *
 */
public class Node {
    /**
     *
     */
    private Node left, right, up, down;

    /**
     *
     */
    private Header header;

    /**
     *
     */
    private int row;

    /**
     *
     */
    public Node() {
        this(-1 , null);
    }

    /**
     *
     * @param row
     */
    public Node(int row) {
        this(row, null);
    }

    /**
     *
     * @param row
     * @param header
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
     *
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     *
     * @return
     */
    public Header getHeader() {
        return this.header;
    }

    /**
     *
     * @param header
     */
    public void setHeader(Header header) {
        this.header = header;
    }

    /**
     *
     * @return
     */
    public Node getDown() {
        return down;
    }

    /**
     *
     * @return
     */
    public Node getRight() {
        return right;
    }

    /**
     *
     * @param left
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     *
     * @param right
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     *
     * @return
     */
    public Node getUp() {
        return up;
    }

    /**
     *
     * @param down
     */
    public void setDown(Node down) {
        this.down = down;
    }

    /**
     *
     * @param up
     */
    public void setUp(Node up) {
        this.up = up;
    }

    /**
     *
     * @return
     */
    public Node getLeft() {
        return left;
    }
}
