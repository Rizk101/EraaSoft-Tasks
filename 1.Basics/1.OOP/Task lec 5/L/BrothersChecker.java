public class BrothersChecker {

    private String secondName1;
    private String secondName2;

    public BrothersChecker(String secondName1, String secondName2) {
        this.secondName1 = secondName1;
        this.secondName2 = secondName2;
    }

    public void printResult() {

        if (secondName1.equals(secondName2)) {
            System.out.println("ARE Brothers");
        } else {
            System.out.println("NOT");
        }
    }
}