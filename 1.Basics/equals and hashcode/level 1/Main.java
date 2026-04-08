//Level 1: Basics of equals & hashCode
//Create a Person class with fields: id, name
//Define what makes two persons equal (by id only)
//Compare two different objects with same values
//Check behavior before overriding equals()
//Then check behavior after
//Create multiple Person objects with same id
//Test equality manually using .equals()
//Think:
public class Main {
    public static void main(String[] args){

        Person p1 = new Person(1, "Ali");
        Person p2 = new Person(1, "Ahmed");
        Person p3 = new Person(1, "Mona");

        System.out.println(p1.equals(p2));
        System.out.println(p2.equals(p3));

    }
}
