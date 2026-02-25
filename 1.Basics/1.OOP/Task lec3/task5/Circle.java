public class Circle {

    private double radius;
    private static final double PI = 3.141592653;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return PI * radius * radius;
    }

    public void printArea() {
        System.out.printf("%.9f\n", calculateArea());
    }
}
