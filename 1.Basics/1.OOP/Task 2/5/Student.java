public class Student extends PublicData {
    private int age;

    public Student() {
    }

    public Student(String id, String name, String phone, int age) {
        super(id, name, phone);
        this.age = age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Incorrect Age");
        }
    }

    public int getAge() {
        return age;
    }
}