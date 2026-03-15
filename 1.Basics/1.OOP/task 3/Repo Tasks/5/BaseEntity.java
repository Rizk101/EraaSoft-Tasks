public class BaseEntity {
    protected String id;
    protected String name;

    public BaseEntity() {
    }
    public BaseEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        } else {
            System.out.println("Incorrect ID");
        }
    }
    public void setName(String name) {
        if (name != null && name.length() >= 3) {
            this.name = name;
        } else {
            System.out.println("Incorrect Name");
        }
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}