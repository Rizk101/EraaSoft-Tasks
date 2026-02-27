import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sum sum = new Sum();
        System.out.println("INSERT NUMBER 1");
        int x = scanner.nextInt();
        System.out.println("INSERT NUMBER 2");
        int y = scanner.nextInt();
        System.out.println("INSERT NUMBER 3");
        int z = scanner.nextInt();

        sum.setSum(x,y,z);
        System.out.println("THE SUM = " + sum.getSum());

    }
}
