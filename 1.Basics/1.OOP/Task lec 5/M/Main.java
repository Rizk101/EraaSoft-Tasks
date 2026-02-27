import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        char x = input.next().charAt(0);

        CharacterChecker checker = new CharacterChecker(x);

        checker.printResult();

        input.close();
    }
}