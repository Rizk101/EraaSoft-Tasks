import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("------ Person ------");
        Person person = new Person();

        System.out.print("Person ID: ");
        String personId = scanner.next();

        System.out.print("Person Name: ");
        String personName = scanner.next();

        person.setId(personId);
        person.setName(personName);

        System.out.println("\n------ Player ------");
        Player player = new Player();

        System.out.print("Player ID: ");
        String playerId = scanner.next();

        System.out.print("Player Name: ");
        String playerName = scanner.next();

        System.out.print("Player Phone (+20xxxxxxxxxxx): ");
        String playerPhone = scanner.next();

        System.out.print("Player Number: ");
        String playerNumber = scanner.next();

        player.setId(playerId);
        player.setName(playerName);
        player.setPhone(playerPhone);
        player.setNumber(playerNumber);

        System.out.println("\n------ Student ------");
        Student student = new Student();

        System.out.print("Student ID: ");
        String studentId = scanner.next();

        System.out.print("Student Name: ");
        String studentName = scanner.next();
        System.out.print("Student Phone (+20xxxxxxxxxxx): ");
        String studentPhone = scanner.next();
        System.out.print("Student Age: ");
        int studentAge = scanner.nextInt();
        student.setId(studentId);
        student.setName(studentName);
        student.setPhone(studentPhone);
        student.setAge(studentAge);

        System.out.println("========== OUTPUT ==========");
        System.out.println("Person  ID: " + person.getId() + ", Name: " + person.getName());

        System.out.println("Player  ID: " + player.getId() + ", Name: " + player.getName()
                + ", Phone: " + player.getPhone() + ", Number: " + player.getNumber());

        System.out.println("Student  ID: " + student.getId() + ", Name: " + student.getName()
                + ", Phone: " + student.getPhone() + ", Age: " + student.getAge());
    }
}