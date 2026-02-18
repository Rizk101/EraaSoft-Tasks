import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the data");


        int intValue = input.nextInt();
        long longValue = input.nextLong();
        char charValue = input.next().charAt(0);
        float floatValue = input.nextFloat();
        double doubleValue = input.nextDouble();

        DataPrinter data = new DataPrinter(intValue, longValue, charValue, floatValue, doubleValue);

        System.out.println("Enter the data");

        data.printData();


    }
}

