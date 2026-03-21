import java.util.Scanner;

public class Main {

    public static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int A = scanner.nextInt();

        int B = scanner.nextInt();

        int gcd = findGCD(A, B);

        System.out.println(gcd);

        scanner.close();
    }
}