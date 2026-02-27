public class CharConverter {

    private char x;

    public CharConverter(char x) {
        this.x = x;
    }

    public void printResult() {

        if (x >= 'a' && x <= 'z') {
            // small → capital
            System.out.println((char)(x - 32));
        } else {
            // capital → small
            System.out.println((char)(x + 32));
        }
    }
}