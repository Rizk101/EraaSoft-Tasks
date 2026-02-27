import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        String f1 = input.next();
        String s1 = input.next();

        String f2 = input.next();
        String s2 = input.next();

        BrothersChecker checker = new BrothersChecker(s1, s2);

        checker.printResult();

    }
}