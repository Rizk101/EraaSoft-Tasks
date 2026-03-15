import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SocialMedia face = new facebook();
        System.out.println("Enter Your Facebook ID");
        int fbid = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Post Your Facebook Text");
        String fbtxt = scanner.nextLine();

        System.out.println("Post Your Facebook Image");
        String fbimg = scanner.nextLine();

        face.setPostData(fbid, fbtxt, fbimg);
        face.showPostData();

        System.out.println("----------------------");

        SocialMedia linked = new LinkedIn();
        System.out.println("Enter Your LinkedIn ID");
        int liid = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Post Your LinkedIn Text");
        String litxt = scanner.nextLine();

        System.out.println("Post Your LinkedIn Image");
        String liimg = scanner.nextLine();

        linked.setPostData(liid, litxt, liimg);
        linked.showPostData();

        scanner.close();
    }
}