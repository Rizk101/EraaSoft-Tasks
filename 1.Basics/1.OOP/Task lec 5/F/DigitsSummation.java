public class DigitsSummation {
    private long n;
    private long m;
    public DigitsSummation(long n, long m) {
        this.n = n;
        this.m = m;
    }
    public long calculateSum() {
        long lastDigitN = n % 10;
        long lastDigitM = m % 10;

        return lastDigitN + lastDigitM;
    }
    public void printResult() {
        System.out.println(calculateSum());
    }
}