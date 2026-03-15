public class Calculator {

    private int a;
    private char operator;
    private int b;

    public Calculator(int a, char operator, int b) {
        this.a = a;
        this.operator = operator;
        this.b = b;
    }

    public void printResult() {

        int result = 0;

        if (operator == '+') {
            result = a + b;
        } else if (operator == '-') {
            result = a - b;
        } else if (operator == '*') {
            result = a * b;
        } else if (operator == '/') {
            result = a / b;
        }

        System.out.println(result);
    }
}