import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        HashSet<Person> set = new HashSet<>();

        set.add(new Person(1, "Ali"));
        set.add(new Person(1, "Ahmed"));
        set.add(new Person(2, "Mona"));
        set.add(new Person(3, "Sara"));
        set.add(new Person(2, "Khaled"));
        set.add(new Person(4, "Ali"));
        set.add(new Person(5, "Omar"));
        set.add(new Person(3, "Hana"));
        set.add(new Person(6, "Ali"));
        set.add(new Person(1, "Ziad"));

        System.out.println("Size: " + set.size());

        for (Person p : set) {
            System.out.println(p.id + " - " + p.name);
        }
    }
}