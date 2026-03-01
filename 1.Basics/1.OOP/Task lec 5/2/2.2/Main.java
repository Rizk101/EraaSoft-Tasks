import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        Scanner scanner = new Scanner(System.in);

        System.out.println("******Welcome Teacher******");
        System.out.println("Please enter your details ");

        System.out.println("Enter Your ID ");
        Long id = scanner.nextLong();
        System.out.println("Enter Your Name ");
        String name = scanner.next();
        System.out.println("Enter Your Age ");
        float age = scanner.nextFloat();
        System.out.println("Enter your Phone Number ");
        String PhoneNumber = scanner.next();
        System.out.println("Enter Your Salary");
        float salary = scanner.nextFloat();

        teacher.setId(id);
        teacher.setName(name);
        teacher.setAge(age);
        teacher.setPhoneNumber(PhoneNumber);
        teacher.setSalary(salary);

        System.out.println("Your id is " + teacher.getId());
        System.out.println("Your Name is " + teacher.getName());
        System.out.println("your age is " + teacher.getage());
        System.out.println("Your Phone Number is " + teacher.getPhoneNumber());
        System.out.println("Your Salary = " + teacher.getSalary());

    }
}

