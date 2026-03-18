import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int X = input.nextInt();
        boolean isPrime = true;

        for(int i = 2; i <= Math.sqrt(X); i++) {
            if(X % i == 0) {
                isPrime = false;
                break;
            }
        }

        if(isPrime) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        input.close();
    }
}