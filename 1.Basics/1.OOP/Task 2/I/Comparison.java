public class Comparison {

    private int a;
    private int b;

    public Comparison(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void printResult() {

        if (a >= b) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}