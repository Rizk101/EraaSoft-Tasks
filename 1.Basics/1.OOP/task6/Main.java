import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long a = input.nextLong();
        long b = input.nextLong();
        long c = input.nextLong();
        long d = input.nextLong();
        DifferenceCalculator calc = new DifferenceCalculator(a, b, c, d);
        calc.printResult();
    }
}
