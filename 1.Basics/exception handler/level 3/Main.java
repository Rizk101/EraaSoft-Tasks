public class Main {
    public static void printUpperCase(String text) {
        try {
            System.out.println(text.toUpperCase());
        } catch (NullPointerException e) {
            System.out.println("Error: String is null.");
        }
    }

    public static void main(String[] args) {
        String str = null;
        printUpperCase(str);
    }
}