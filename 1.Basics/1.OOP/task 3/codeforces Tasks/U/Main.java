import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();

        int totalSum = 0;

        for (int i = 1; i <= N; i++) {
            int num = i;
            int digitSum = 0;

            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }

            if (digitSum >= A && digitSum <= B) {
                totalSum += i;
            }
        }

        System.out.println(totalSum);
    }
}