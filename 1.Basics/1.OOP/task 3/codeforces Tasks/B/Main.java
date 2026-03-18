import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        if (n < 2) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= n; i += 2) {
                System.out.println(i);
            }
        }
    }
}