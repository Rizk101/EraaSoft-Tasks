public class CharacterChecker {

    private char x;

    public CharacterChecker(char x) {
        this.x = x;
    }

    public void printResult() {

        if (x >= '0' && x <= '9') {
            System.out.println("IS DIGIT");
        } else {
            System.out.println("ALPHA");

            if (x >= 'A' && x <= 'Z') {
                System.out.println("IS CAPITAL");
            } else {
                System.out.println("IS SMALL");
            }
        }
    }
}