public class BaseEntity {
    private String id;
    private String name;

    public void setId(String id) {
        if(id != null && !id.isEmpty()){
            this.id = id;
        }else{
            System.out.println("Incorrect ID");
        }
    }

    public void setName(String name) {
        if(name != null && name.length() >=3){
            this.name = name;
        }else{
            System.out.println("Incorect Nmae");
        }
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
