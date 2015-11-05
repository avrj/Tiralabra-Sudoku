import domain.Header;
import logic.DLX;

public class Sudoku {
    public static void main(String[] args) {
        Header rootHeader = new Header();
        Header header_A = new Header();
        Header header_B = new Header();

        rootHeader.setRight(header_A);

        header_A.setLeft(rootHeader);
        header_A.setRight(header_B);
        header_B.setLeft(header_A);

        System.out.println("header_A");
        System.out.println("Left: " + header_A.getLeft().equals(header_A));
        System.out.println("Right: " + header_A.getRight().equals(header_A));
        System.out.println("Up: " + header_A.getUp().equals(header_A));
        System.out.println("Down: " + header_A.getDown().equals(header_A));
        System.out.println("Header: " + header_A.getHeader().equals(header_A));

        System.out.println("");
        System.out.println("header_B");
        System.out.println("Left: " + header_B.getLeft().equals(header_B));
        System.out.println("Right: " + header_B.getRight().equals(header_B));
        System.out.println("Up: " + header_B.getUp().equals(header_B));
        System.out.println("Down: " + header_B.getDown().equals(header_B));
        System.out.println("Header: " + header_B.getHeader().equals(header_B));

        DLX dlx = new DLX();
        dlx.coverColumn(header_A);
        System.out.println("");
        System.out.println("Covering header_A");
        System.out.println("");

        System.out.println("header_A");
        System.out.println("Left: " + header_A.getLeft().equals(header_A));
        System.out.println("Right: " + header_A.getRight().equals(header_A));
        System.out.println("Up: " + header_A.getUp().equals(header_A));
        System.out.println("Down: " + header_A.getDown().equals(header_A));
        System.out.println("Header: " + header_A.getHeader().equals(header_A));

        System.out.println("");
        System.out.println("header_B");
        System.out.println("Left: " + header_B.getLeft().equals(header_B));
        System.out.println("Right: " + header_B.getRight().equals(header_B));
        System.out.println("Up: " + header_B.getUp().equals(header_B));
        System.out.println("Down: " + header_B.getDown().equals(header_B));
        System.out.println("Header: " + header_B.getHeader().equals(header_B));

        System.out.println(header_B.getLeft().equals(rootHeader));
        System.out.println(rootHeader.getRight().equals(header_B));
    }
}
