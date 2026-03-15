import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Player player = new Player();
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------Player--------");
        System.out.println("What's Player Number?");
        String Number = scanner.next();
        System.out.println("Player ID ?");
        String id = scanner.next();
        System.out.println("Player Name ?");
        String name = scanner.next();
        System.out.println("-----------Student--------");
        System.out.println("Student Age ?");
        int age = scanner.nextInt();
        System.out.println("Student ID ");
        String StudentId = scanner.next();;
        System.out.println("Student Name ?");
        String StudentName = scanner.next();

        player.setNumber(Number);
        player.setId(id);
        player.setName(name);
        student.setAge(age);
        student.setId(StudentId);
        student.setName(StudentName);

        System.out.println("-----------Player--------");
        System.out.println("Player Number is " + player.getNumber());
        System.out.println("player ID is " + player.getId());
        System.out.println("player Nmae is " + player.getName());
        System.out.println("-----------Student--------");
        System.out.println("Student age is " + student.getAge());
        System.out.println("Student ID is " + student.getId());
        System.out.println("Student Name is " + student.getName());







    }

    }
