public class Main {
    public static void main(String[] args) {
        try {
            try {
                int result = 10 / 0;
                System.out.println(result);
            } catch (NullPointerException e) {
                System.out.println("Inner catch: Null pointer exception.");
            }
        } catch (ArithmeticException e) {
            System.out.println("Outer catch: Arithmetic exception caught.");
        }
    }
}