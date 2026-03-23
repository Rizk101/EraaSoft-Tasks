import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String S = sc.next();
        int N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();

            for (int j = 0; j < x; j++) {
                System.out.print(S);
            }

            System.out.println();
        }
    }
}