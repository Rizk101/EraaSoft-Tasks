import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int X = sc.nextInt();
            int Y = sc.nextInt();

            int start = Math.min(X, Y);
            int end = Math.max(X, Y);

            int sum = 0;

            for (int i = start + 1; i < end; i++) {
                if (i % 2 != 0) {
                    sum += i;
                }
            }

            System.out.println(sum);
        }
    }
}