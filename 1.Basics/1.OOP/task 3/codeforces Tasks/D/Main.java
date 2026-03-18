import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            if (n == 1999) {
                System.out.println("Correct");
                return;
            } else {
                System.out.println("Wrong");
            }
        }

    }
}