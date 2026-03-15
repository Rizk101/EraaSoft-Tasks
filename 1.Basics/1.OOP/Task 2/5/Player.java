public class Player extends PublicData {
    private String number;

    public Player() {
    }

    public Player(String id, String name, String phone, String number) {
        super(id, name, phone);
        this.number = number;
    }
    public void setNumber(String number) {
        if (number != null && !number.isEmpty()) {
            this.number = number;
        } else {
            System.out.println("Incorrect Player Number");
        }
    }
    public String getNumber() {
        return number;
    }
}