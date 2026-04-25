import Service.AplicationService;
import Service.impl.EwalletAplicationServiceImpl;

public class Main {
    public static void main(String[] args){
        AplicationService aplicationService = new EwalletAplicationServiceImpl();
        aplicationService.start();
    }
}
