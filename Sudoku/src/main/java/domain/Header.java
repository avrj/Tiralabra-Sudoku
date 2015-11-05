package domain;

/**
 * Represents the header column in doubly linked list
 */
public class Header extends Node {
    /**
     * Number of nodes linked in to the column header
     */
    private int size;

    /**
     * Name of header
     */
    private int name;

    /**
     * Initializes header with name 0
     */
    public Header() {
        this(0);
    }

    /**
     * Initializes header with name
     *
     * @param name Name of header
     */
    public Header(int name) {
        this.name = name;
        this.size = 0;
        this.setHeader(this);
    }

    /**
     *
     * @return Name of header
     */
    public int getName() {
        return name;
    }

    /**
     * Sets the name of header
     *
     * @param name Name of header
     */
    public void setName(int name) {
        this.name = name;
    }

    /**
     *
     * @return Count of nodes linked to header
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size Sets the count of linked nodes
     */
    public void setSize(int size) {
        this.size = size;
    }
}
