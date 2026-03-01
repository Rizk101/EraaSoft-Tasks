import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PrivateSchool privateSchool = new PrivateSchool();

        System.out.println("---- Private School ----");
        System.out.print("Enter ID: ");
        String privateId = scanner.next();

        System.out.print("Enter Name: ");
        String privateName = scanner.next();

        privateSchool.setId(privateId);
        privateSchool.setName(privateName);

        PublicSchoolStudent publicStudent = new PublicSchoolStudent();

        System.out.println("\n---- Public School Student ----");
        System.out.print("Enter ID: ");
        String publicId = scanner.next();

        System.out.print("Enter Name: ");
        String publicName = scanner.next();

        publicStudent.setId(publicId);
        publicStudent.setName(publicName);

        System.out.println("\n===== OUTPUT =====");
        System.out.println("Private School -> ID: " + privateSchool.getId()
                + ", Name: " + privateSchool.getName());

        System.out.println("Public School Student -> ID: " + publicStudent.getId()
                + ", Name: " + publicStudent.getName());
    }
}