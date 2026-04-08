import java.util.Objects;

class Person {
    int id;
    String name;

    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person p = (Person) obj;
        return this.id == p.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}