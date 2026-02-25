import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int number = scanner.nextInt();
        scanner.nextLine();
        String name = scanner.nextLine();

        Player player = new Player(number, name);

        player.printPlayer();

    }
}