import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        boolean found = false;

        for (int i = A; i <= B; i++) {
            if (isLucky(i)) {
                System.out.print(i + " ");
                found = true;
            }
        }

        if (!found) {
            System.out.println(-1);
        }
    }

    public static boolean isLucky(int num) {
        String s = String.valueOf(num);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '4' && s.charAt(i) != '7') {
                return false;
            }
        }

        return true;
    }
}