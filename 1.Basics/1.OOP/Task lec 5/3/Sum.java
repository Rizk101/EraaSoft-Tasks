public class Sum {
    private int x,y,z;
    private int sum;

    public void setSum(int x,int y,int z){
        if(x%2 ==0 && y%2 ==0 && z%2 ==0){
            sum = x + y + z;
        }else{
            System.out.println("ERROR");
        }
    }

    public int getSum() {
        return sum;
    }

}
