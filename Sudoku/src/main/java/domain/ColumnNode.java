package domain;

/**
 * Represents the header column in doubly linked list
 */
public class ColumnNode extends Node {
    /**
     * Number of nodes linked in to the column header
     */
    private int size = 0;

    /**
     * Returns the count of nodes linked to header
     *
     * @return Count of nodes linked to header
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the count of linked nodes
     *
     * @param size Count of linked nodes
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Which constraint this node applies to
     */
    private int constraint = -1;

    /**
     * The number of this column
     */
    private int number = -1;

    /**
     * Position in matrix
     */
    private int position = -1;

    /**
     * Returns the position
     * @return Position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the position
     * @param position The position of this column
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the number of this column
     * @return Number of this column
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sets the number of this column
     * @param number Column number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Returns the constraint of this column
     * @return Constraint of this column
     */
    public int getConstraint() {
        return constraint;
    }

    /**
     * Sets the constraint of this column
     * @param constraint Constraint of this column
     */
    public void setConstraint(int constraint) {
        this.constraint = constraint;
    }
}
