public class Student extends  PublicData {

    private int age;

    public void setAge(int age) {
        if(age >= 18){
            this.age = age;
        }else{
            System.out.println("ERROR");
        }
    }

    public int getAge() {
        return age;
    }
}
