public class Player extends PublicData {

    private String Number;

    public void setNumber(String Number) {
        if(Number.length() == 2){
            this.Number = Number;
        }else{
            System.out.println("ERROR");
        }
    }
    public String getNumber() {
        return Number;
    }
}
