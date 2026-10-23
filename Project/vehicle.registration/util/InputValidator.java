package util;

import exception.InvalidInputException;

import java.time.LocalDate;

public class InputValidator {

    public static void validatePlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }

        String normalizedPlate = plateNumber.trim().toUpperCase();
        if (!normalizedPlate.matches("[A-Z0-9-]{3,10}")) {
            throw new InvalidInputException("Plate number must be 3-10 alphanumeric characters");
        }
    }

    public static void validateOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().length() < 3
                || !ownerName.trim().matches("[A-Za-z]+(?: [A-Za-z]+)*")) {
            throw new InvalidInputException("Owner name must contain only letters");
        }
    }

    public static void validateVehicleType(String vehicleType) {
        if (vehicleType == null
                || (!vehicleType.trim().equalsIgnoreCase("Car")
                && !vehicleType.trim().equalsIgnoreCase("Truck")
                && !vehicleType.trim().equalsIgnoreCase("Motorcycle"))) {
            throw new InvalidInputException("Invalid type. Use: Car, Truck, Motorcycle");
        }
    }

    public static void validateRegistrationYear(int registrationYear) {
        int currentYear = LocalDate.now().getYear();
        if (registrationYear < 1990 || registrationYear > currentYear) {
            throw new InvalidInputException(
                    "Year must be between 1990 and " + currentYear);
        }
    }
}
