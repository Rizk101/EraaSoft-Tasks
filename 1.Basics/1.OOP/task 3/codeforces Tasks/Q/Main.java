import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for (int i = 1 ; i <= T ; i++ ){

            int n = scanner.nextInt();
            if (n==0){
                System.out.println(0);
                continue;
            }
            while (n > 0){
                int digit = n%10;
                System.out.print(digit + " ");
                n = n/10;
            }
            System.out.println();
        }
    }
}