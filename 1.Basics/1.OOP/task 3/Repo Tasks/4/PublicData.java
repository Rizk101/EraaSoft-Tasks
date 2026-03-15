public class PublicData {
    private String id;
    private String name;

    public void setId(String id) {
        if(id.length() >= 4){
            this.id = id;
        }else{
            System.out.println("Wrong ID");
        }
    }

    public void setName(String name) {
        if(name.length() >= 4){
            this.name = name;
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
