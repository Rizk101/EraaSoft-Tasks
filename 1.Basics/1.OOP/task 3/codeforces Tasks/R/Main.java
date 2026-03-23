import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            if (N <= 0 || M <= 0) {
                break;
            }

            int start = Math.min(N, M);
            int end = Math.max(N, M);

            int sum = 0;

            for (int i = start; i <= end; i++) {
                System.out.print(i + " ");
                sum += i;
            }

            System.out.println("sum =" + sum);
        }
    }
}