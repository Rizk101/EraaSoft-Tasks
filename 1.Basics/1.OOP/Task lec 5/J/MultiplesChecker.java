public class MultiplesChecker {

    private int a;
    private int b;

    public MultiplesChecker(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void printResult() {

        if (a % b == 0 || b % a == 0) {
            System.out.println("Multiples");
        } else {
            System.out.println("No Multiples");
        }
    }
}