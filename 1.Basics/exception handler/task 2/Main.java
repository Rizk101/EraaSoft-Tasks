import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            String str = input.nextLine();

            int num = Integer.parseInt(str);
            System.out.println("Converted number = " + num);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format.");
        }

        input.close();
    }
}