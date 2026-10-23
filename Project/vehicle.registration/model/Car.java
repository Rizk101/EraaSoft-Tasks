package model;

public class Car extends Vehicle {
    private int numberOfDoors;

    public Car(String plateNumber, String ownerName, int registrationYear,
               String status, int numberOfDoors) {
        super(plateNumber, ownerName, "Car", registrationYear, status);
        this.numberOfDoors = numberOfDoors;
    }

    public Car(String plateNumber, String ownerName, int registrationYear, String status) {
        this(plateNumber, ownerName, registrationYear, status, 4);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public String getRegistrationLabel() {
        return "Passenger Car \u2014 Doors: " + numberOfDoors;
    }

    @Override
    public String toString() {
        return super.toString() + " | Doors: " + numberOfDoors;
    }
}
