public class DataPrinter {

    private int intValue;
    private long longValue;
    private char charValue;
    private float floatValue;
    private double doubleValue;

    public DataPrinter(int intValue, long longValue, char charValue, float floatValue, double doubleValue) {
        this.intValue = intValue;
        this.longValue = longValue;
        this.charValue = charValue;
        this.floatValue = floatValue;
        this.doubleValue = doubleValue;
    }

    public void printData() {
        System.out.println(intValue);
        System.out.println(longValue);
        System.out.println(charValue);
        System.out.println(floatValue);
        System.out.println(doubleValue);
    }
}
