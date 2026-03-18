import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int t = input.nextInt();

        for (int i = 0; i < t; i++) {

            int n = input.nextInt();

            long fact = 1;

            for (int j = 1; j <= n; j++) {
                fact = fact * j;
            }

            System.out.println(fact);
        }
    }
}