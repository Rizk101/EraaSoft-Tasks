public class PublicData extends BaseEntity {
    protected String phone;

    public PublicData() {
    }
    public PublicData(String id, String name, String phone) {
        super(id, name);
        this.phone = phone;
    }
    public void setPhone(String phone) {
        if (phone != null && phone.startsWith("+20") && phone.length() == 13) {
            this.phone = phone;
        } else {
            System.out.println("Incorrect Phone");
        }
    }
    public String getPhone() {
        return phone;
    }
}