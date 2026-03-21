import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String star = "";

        for(int i = 1 ; i <= n ; i++)
        {
            star += "*";

        }
        System.out.println(star);
        while (star.length() > 0){
            star = star.substring(0, star.length() - 1);
            System.out.println(star);
        }


    }
}