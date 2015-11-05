package domain;

/**
 *
 */
public class Header extends Node {
    /**
     * Number of nodes linked in to the column header
     */
    private int size;

    /**
     *
     */
    private int name;

    /**
     *
     */
    public Header() {
        this(0);
    }

    /**
     *
     * @param name
     */
    public Header(int name) {
        this.name = name;
        this.size = 0;
        this.setHeader(this);
    }

    /**
     *
     * @return
     */
    public int getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(int name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }
}
