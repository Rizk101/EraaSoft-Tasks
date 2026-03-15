import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        MaxMinFinder finder = new MaxMinFinder(a, b, c);

        finder.printResult();
    }
}