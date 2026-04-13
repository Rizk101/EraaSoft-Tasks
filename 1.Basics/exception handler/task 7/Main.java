import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Main {
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older.");
        } else {
            System.out.println("Access granted.");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Enter your age: ");
            int age = input.nextInt();

            checkAge(age);
        } catch (InvalidAgeException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }

        input.close();
    }
}