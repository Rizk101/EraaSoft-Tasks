import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = input.nextInt();

        System.out.print("Enter Student Name: ");
        String name = input.next();

        System.out.print("Enter Student Age: ");
        int age = input.nextInt();

        // Create object
        Student student1 = new Student(id, name, age);

        // Print data
        student1.printStudentInfo();


    }
}