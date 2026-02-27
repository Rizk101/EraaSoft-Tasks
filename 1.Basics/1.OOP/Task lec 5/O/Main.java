import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();
        char op = input.next().charAt(0);
        int b = input.nextInt();

        Calculator calc = new Calculator(a, op, b);

        calc.printResult();

    }
}