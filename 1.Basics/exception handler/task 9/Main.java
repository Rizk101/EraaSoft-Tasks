public class Main {

    public static void method1() throws Exception {
        throw new Exception("Exception from method1");
    }

    public static void method2() throws Exception {
        method1();
    }

    public static void main(String[] args) {
        try {
            method2();
        } catch (Exception e) {
            System.out.println("Caught in main: " + e.getMessage());
        }
    }
}