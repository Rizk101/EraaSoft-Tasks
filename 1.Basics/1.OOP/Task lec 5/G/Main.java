import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        long n = input.nextLong();

        Summation summation = new Summation(n);

        summation.printResult();

    }
}