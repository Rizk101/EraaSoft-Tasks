import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int original = n;
        int reverce = 0;

        while (n > 0)
        {
            int digit = n % 10;
            reverce = reverce * 10 + digit;
            n = n / 10;
        }
        System.out.println(reverce);

        if (reverce == original)
        {
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}