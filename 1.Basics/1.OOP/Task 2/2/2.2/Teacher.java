
public class Teacher {

   private Long id;
   private String name;
   private float age;
   private String PhoneNumber;
   private float salary;


    public void setId(Long id) {
        if(id > 0){
            this.id = id;

        }else{
            System.out.println("Incorrect Data");
        }

    }
    public void setName(String name) {
        if(name.length() >= 3 && name.matches("[a-z]+")){
            this.name = name;
        }else{
            System.out.println("Incorrect Name");
        }
    }
    public void setAge(float age) {
        if(age >= 25 && age <= 60){
            this.age = age;
        }else {
            System.out.println("Incorrect Age");
        }
    }
    public void setSalary(float salary) {
        if(salary >= 3000){
            this.salary = salary;
        }else{
            System.out.println("Incorrect Salary");
        }

    }
    public void setPhoneNumber(String  PhoneNumber) {
        if(PhoneNumber.length()==13 && PhoneNumber.startsWith("+20")){
            this.PhoneNumber = PhoneNumber;
        }else{
            System.out.println("Incorrect Number");
        }
    }
// *******************************************************************************************
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public float getage(){
        return age;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public float getSalary() {
        return salary;
    }
}
