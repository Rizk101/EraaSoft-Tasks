public class Main {
    public static void main(String[] args) {
        try {
            String text = null;
            System.out.println(text.length());

            int result = 10 / 0; // ArithmeticException
            System.out.println(result);
        } catch (NullPointerException e) {
            System.out.println("Error: String is null.");
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        }
    }
}