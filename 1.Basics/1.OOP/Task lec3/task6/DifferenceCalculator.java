public class DifferenceCalculator {

    private long a;
    private long b;
    private long c;
    private long d;

    public DifferenceCalculator(long a, long b, long c, long d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public long calculateDifference() {
        return (a * b) - (c * d);
    }

    public void printResult() {
        System.out.println("Difference = " + calculateDifference());
    }
}
