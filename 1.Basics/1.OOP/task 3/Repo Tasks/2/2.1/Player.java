public class Player {

    private int number;
    private String name;

    public Player(int number, String name) {

        if (number > 0) {
            this.number = number;
        } else {
            System.out.println("Invalid number! Must be greater than 0.");
            System.exit(0);
        }

        if (name.length() > 5) {
            this.name = name;
        } else {
            System.out.println("Invalid name! Length must be greater than 5.");
            System.exit(0);
        }
    }

    public void printPlayer() {
        System.out.println("Number: " + number);
        System.out.println("Name: " + name);
    }
}