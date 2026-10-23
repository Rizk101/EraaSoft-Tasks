package model;

public class Truck extends Vehicle {
    private double cargoCapacityTons;

    public Truck(String plateNumber, String ownerName, int registrationYear,
                 String status, double cargoCapacityTons) {
        super(plateNumber, ownerName, "Truck", registrationYear, status);
        this.cargoCapacityTons = cargoCapacityTons;
    }

    public Truck(String plateNumber, String ownerName, int registrationYear, String status) {
        this(plateNumber, ownerName, registrationYear, status, 0.0);
    }

    public double getCargoCapacityTons() {
        return cargoCapacityTons;
    }

    @Override
    public String getRegistrationLabel() {
        return "Commercial Truck \u2014 Cargo: " + cargoCapacityTons + " tons";
    }

    @Override
    public String toString() {
        return super.toString() + " | Cargo: " + cargoCapacityTons + " tons";
    }
}
