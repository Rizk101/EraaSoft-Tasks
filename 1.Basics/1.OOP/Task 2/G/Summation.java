public class Summation {

    private long n;

    public Summation(long n) {
        this.n = n;
    }

    public long calculateSum() {
        return n * (n + 1) / 2;
    }

    public void printResult() {
        System.out.println(calculateSum());
    }
}