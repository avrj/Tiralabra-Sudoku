import domain.ColumnNode;
import logic.DLX;

public class Sudoku {
    public static void main(String[] args) {
        ColumnNode rootColumnNode = new ColumnNode();
        ColumnNode columnNode_A = new ColumnNode();
        ColumnNode columnNode_B = new ColumnNode();

        rootColumnNode.setRight(columnNode_A);

        columnNode_A.setLeft(rootColumnNode);
        columnNode_A.setRight(columnNode_B);
        columnNode_B.setLeft(columnNode_A);

        System.out.println("columnNode_A");
        System.out.println("Left: " + columnNode_A.getLeft().equals(columnNode_A));
        System.out.println("Right: " + columnNode_A.getRight().equals(columnNode_A));
        System.out.println("Up: " + columnNode_A.getUp().equals(columnNode_A));
        System.out.println("Down: " + columnNode_A.getDown().equals(columnNode_A));
        System.out.println("ColumnNode: " + columnNode_A.getColumnNode().equals(columnNode_A));

        System.out.println("");
        System.out.println("columnNode_B");
        System.out.println("Left: " + columnNode_B.getLeft().equals(columnNode_B));
        System.out.println("Right: " + columnNode_B.getRight().equals(columnNode_B));
        System.out.println("Up: " + columnNode_B.getUp().equals(columnNode_B));
        System.out.println("Down: " + columnNode_B.getDown().equals(columnNode_B));
        System.out.println("ColumnNode: " + columnNode_B.getColumnNode().equals(columnNode_B));

        DLX dlx = new DLX();
        dlx.coverColumn(columnNode_A);
        System.out.println("");
        System.out.println("Covering columnNode_A");
        System.out.println("");

        System.out.println("columnNode_A");
        System.out.println("Left: " + columnNode_A.getLeft().equals(columnNode_A));
        System.out.println("Right: " + columnNode_A.getRight().equals(columnNode_A));
        System.out.println("Up: " + columnNode_A.getUp().equals(columnNode_A));
        System.out.println("Down: " + columnNode_A.getDown().equals(columnNode_A));
        System.out.println("ColumnNode: " + columnNode_A.getColumnNode().equals(columnNode_A));

        System.out.println("");
        System.out.println("columnNode_B");
        System.out.println("Left: " + columnNode_B.getLeft().equals(columnNode_B));
        System.out.println("Right: " + columnNode_B.getRight().equals(columnNode_B));
        System.out.println("Up: " + columnNode_B.getUp().equals(columnNode_B));
        System.out.println("Down: " + columnNode_B.getDown().equals(columnNode_B));
        System.out.println("ColumnNode: " + columnNode_B.getColumnNode().equals(columnNode_B));

        System.out.println(columnNode_B.getLeft().equals(rootColumnNode));
        System.out.println(rootColumnNode.getRight().equals(columnNode_B));

        dlx = new DLX();

        dlx.buildMatrix(new int[][]{
                {1, 1, 1, 0},
                {1, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {1, 1, 1, 1}
        });
    }
}
