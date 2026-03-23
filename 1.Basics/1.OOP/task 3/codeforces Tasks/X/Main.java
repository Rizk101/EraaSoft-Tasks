import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();

            int count = 0;

            while (N > 0) {
                if (N % 2 == 1) {
                    count++;
                }
                N /= 2;
            }

            int result = (1 << count) - 1;

            System.out.println(result);
        }
    }
}