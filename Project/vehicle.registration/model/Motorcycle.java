package model;

public class Motorcycle extends Vehicle {
    private String engineType;

    public Motorcycle(String plateNumber, String ownerName, int registrationYear,
                      String status, String engineType) {
        super(plateNumber, ownerName, "Motorcycle", registrationYear, status);
        this.engineType = engineType;
    }

    public Motorcycle(String plateNumber, String ownerName, int registrationYear, String status) {
        this(plateNumber, ownerName, registrationYear, status, "Sport");
    }

    public String getEngineType() {
        return engineType;
    }

    @Override
    public String getRegistrationLabel() {
        return "Motorcycle \u2014 Engine: " + engineType;
    }

    @Override
    public String toString() {
        return super.toString() + " | Engine: " + engineType;
    }
}
