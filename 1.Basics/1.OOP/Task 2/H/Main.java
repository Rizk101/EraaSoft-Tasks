import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();
        int b = input.nextInt();

        DivisionOperations op = new DivisionOperations(a, b);

        op.printResults();
    }
}