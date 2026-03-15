public class MaxMinFinder {

    private int a;
    private int b;
    private int c;

    public MaxMinFinder(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void printResult() {

        int min = Math.min(a, Math.min(b, c));
        int max = Math.max(a, Math.max(b, c));

        System.out.println(min + " " + max);
    }
}