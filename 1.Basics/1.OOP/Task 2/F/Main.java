import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long n = input.nextLong();
        long m = input.nextLong();

        DigitsSummation ds = new DigitsSummation(n, m);

        ds.printResult();

    }
}