//please create public clup and private clup to save player
//Look
//Please make sure of them to not use any codeing of other 🙂


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Club club;

        System.out.println("1. Public Club");
        System.out.println("2. Private Club");
        int choice = input.nextInt();

        if (choice == 1) {
            club = new PublicClub("Public Club");
        } else {
            club = new PrivateClub("Private Club");
        }

        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Age: ");
        int age = input.nextInt();

        Player p = new Player(id, name, age);

        club.addPlayer(p);

        club.showPlayers();

        input.close();
    }
}