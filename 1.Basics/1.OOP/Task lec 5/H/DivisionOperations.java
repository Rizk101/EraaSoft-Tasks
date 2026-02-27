public class DivisionOperations {

    private int a;
    private int b;

    public DivisionOperations(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public void printResults() {

        double result = (double) a / b;

        long floorValue = (long) Math.floor(result);
        long ceilValue = (long) Math.ceil(result);
        long roundValue = Math.round(result);

        System.out.println("floor " + a + " / " + b + " = " + floorValue);
        System.out.println("ceil " + a + " / " + b + " = " + ceilValue);
        System.out.println("round " + a + " / " + b + " = " + roundValue);
    }
}