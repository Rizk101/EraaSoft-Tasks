import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ClubFc fcPlayer = new ClubFc();
        ClubRel relPlayer = new ClubRel();



        System.out.println("---- ClubFc Player ----");
        System.out.print("Enter ID: ");
        fcPlayer.setId(scanner.next());

        System.out.print("Enter Name: ");
        fcPlayer.setName(scanner.next());

        System.out.print("Enter Number: ");
        fcPlayer.setNumber(scanner.next());

        System.out.print("Enter Fcode: ");
        fcPlayer.setFcode(scanner.next());



        System.out.println("\n---- ClubRel Player ----");
        System.out.print("Enter ID: ");
        relPlayer.setId(scanner.next());

        System.out.print("Enter Name: ");
        relPlayer.setName(scanner.next());

        System.out.print("Enter Number: ");
        relPlayer.setNumber(scanner.next());

        System.out.print("Enter Rcode: ");
        relPlayer.setRcode(scanner.next());


        System.out.println("\n===== OUTPUT =====");

        System.out.println("ClubFc Player -> "
                + fcPlayer.getId() + " , "
                + fcPlayer.getName() + " , "
                + fcPlayer.getNumber() + " , "
                + fcPlayer.getFcode());

        System.out.println("ClubRel Player -> "
                + relPlayer.getId() + " , "
                + relPlayer.getName() + " , "
                + relPlayer.getNumber() + " , "
                + relPlayer.getRcode());
    }
}