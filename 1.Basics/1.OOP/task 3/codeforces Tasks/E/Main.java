import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        int max = input.nextInt();

        for (int i = 1; i < n; i++) {
            int x = input.nextInt();
            if (x > max) {
                max = x;
            }
        }

        System.out.println(max);
    }
}