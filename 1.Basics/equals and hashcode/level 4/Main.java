import java.util.*;

public class Main {
    public static void main(String[] args) {

        // ================= Product =================
        System.out.println("=== Product (HashSet) ===");

        HashSet<Product> products = new HashSet<>();
        products.add(new Product("P100", 5000));
        products.add(new Product("P100", 7000)); // duplicate
        products.add(new Product("P200", 3000));
        products.add(new Product("P300", 4500));

        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("Size = " + products.size());


        // ================= Student =================
        System.out.println("\n=== Student (HashSet) ===");

        HashSet<Student> students = new HashSet<>();
        students.add(new Student(1, "a@gmail.com"));
        students.add(new Student(1, "b@gmail.com")); // duplicate id
        students.add(new Student(2, "a@gmail.com"));

        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println("Size = " + students.size());


        // ================= Car =================
        System.out.println("\n=== Car (HashMap) ===");

        HashMap<Car, String> map = new HashMap<>();

        Car c1 = new Car("ABC123", "Red");
        Car c2 = new Car("ABC123", "Blue"); // same plate
        Car c3 = new Car("XYZ999", "Black");

        map.put(c1, "Kareem");
        map.put(c2, "Ahmed"); // replace
        map.put(c3, "Mona");

        for (Car c : map.keySet()) {
            System.out.println(c + " -> " + map.get(c));
        }

        System.out.println("Size = " + map.size());

        Car search = new Car("ABC123", "Any");
        System.out.println("Retrieve: " + map.get(search));
    }
}