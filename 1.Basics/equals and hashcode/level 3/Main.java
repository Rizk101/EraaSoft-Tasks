import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Person, String> map = new HashMap<>();


        Person p1 = new Person(1, "Ali");
        Person p2 = new Person(1, "Ahmed");
        Person p3 = new Person(2, "Mona");

        map.put(p1, "Employee");
        map.put(p2, "Manager");
        map.put(p3, "HR");

        System.out.println("Map size: " + map.size());
        System.out.println("Value of p1: " + map.get(p1));
        System.out.println("Value of p2: " + map.get(p2));
        System.out.println("Value of p3: " + map.get(p3));

        System.out.println("----------------------");


        Person newPerson = new Person(1, "AnyName");
        System.out.println("Retrieve باستخدام object جديد له نفس id:");
        System.out.println(map.get(newPerson));

        System.out.println("----------------------");

        // 3) Modify a key after inserting into HashMap
        Person p4 = new Person(3, "Omar");
        map.put(p4, "Developer");

        System.out.println("Before changing key:");
        System.out.println(map.get(p4));

        p4.id = 99;
        p4.name = "Changed";

        System.out.println("After changing key:");
        System.out.println(map.get(p4));

        System.out.println("----------------------");

        System.out.println("All entries in map:");
        for (Person p : map.keySet()) {
            System.out.println("id = " + p.id + ", name = " + p.name + " => " + map.get(p));
        }
    }
}