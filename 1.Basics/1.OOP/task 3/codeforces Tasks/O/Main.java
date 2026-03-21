import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String star = "*";

        for(int i = 1 ; i <= n ; i++)
        {
            System.out.println(star);
            star += "*";

        }

    }
}