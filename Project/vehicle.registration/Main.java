import exception.DuplicatePlateException;
import exception.InvalidInputException;
import exception.VehicleNotFoundException;
import model.Car;
import model.Motorcycle;
import model.Truck;
import model.Vehicle;
import service.RegistrationService;
import util.InputValidator;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistrationService service = new RegistrationService();

    public static void main(String[] args) {
        loadSampleData();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readChoice();

            switch (choice) {
                case 1:
                    registerVehicle();
                    break;
                case 2:
                    searchVehicle();
                    break;
                case 3:
                    updateOwner();
                    break;
                case 4:
                    deleteVehicle();
                    break;
                case 5:
                    listAllVehicles();
                    break;
                case 6:
                    filterByType();
                    break;
                case 7:
                    showOwnerHistory();
                    break;
                case 8:
                    showExpiredRegistrations();
                    break;
                case 9:
                    service.printStatistics();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting Vehicle Registration System.");
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("========================================");
        System.out.println("   VEHICLE REGISTRATION SYSTEM  v1.0");
        System.out.println("========================================");
        System.out.println(" 1. Register New Vehicle");
        System.out.println(" 2. Search Vehicle by Plate");
        System.out.println(" 3. Update Owner Name");
        System.out.println(" 4. Delete Vehicle");
        System.out.println(" 5. List All Vehicles");
        System.out.println(" 6. Filter by Vehicle Type");
        System.out.println(" 7. Show Owner History");
        System.out.println(" 8. Show Expired Registrations");
        System.out.println(" 9. Statistics Report");
        System.out.println(" 0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static int readChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void registerVehicle() {
        try {
            String plate = readLine("Enter plate number: ").toUpperCase();
            InputValidator.validatePlateNumber(plate);

            String owner = readLine("Enter owner name: ");
            InputValidator.validateOwnerName(owner);

            String type = readLine("Enter vehicle type (Car/Truck/Motorcycle): ");
            InputValidator.validateVehicleType(type);

            System.out.print("Enter registration year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            InputValidator.validateRegistrationYear(year);

            Vehicle vehicle;
            if (type.equalsIgnoreCase("Car")) {
                vehicle = new Car(plate, owner, year, "ACTIVE");
            } else if (type.equalsIgnoreCase("Truck")) {
                vehicle = new Truck(plate, owner, year, "ACTIVE");
            } else {
                vehicle = new Motorcycle(plate, owner, year, "ACTIVE");
            }

            service.registerVehicle(vehicle);
            System.out.println("Vehicle registered successfully.");
            System.out.println(vehicle);
        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (DuplicatePlateException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    private static void searchVehicle() {
        try {
            String plate = readLine("Enter plate number: ").toUpperCase();
            InputValidator.validatePlateNumber(plate);

            Vehicle vehicle = service.findByPlate(plate);
            System.out.println("Vehicle found:");
            System.out.println(vehicle);
            System.out.println("Registration: " + vehicle.getRegistrationLabel());
        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void updateOwner() {
        try {
            String plate = readLine("Enter plate number: ").toUpperCase();
            InputValidator.validatePlateNumber(plate);

            String owner = readLine("Enter new owner name: ");
            InputValidator.validateOwnerName(owner);

            service.updateOwner(plate, owner);
            System.out.println("Owner updated successfully.");
            System.out.println(service.findByPlate(plate));
        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteVehicle() {
        try {
            String plate = readLine("Enter plate number: ").toUpperCase();
            InputValidator.validatePlateNumber(plate);

            service.deleteVehicle(plate);
            System.out.println("Vehicle deleted successfully.");
        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (VehicleNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listAllVehicles() {
        List<Vehicle> vehicles = service.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered.");
            return;
        }

        System.out.println("--- All Registered Vehicles ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private static void filterByType() {
        try {
            String type = readLine("Enter vehicle type (Car/Truck/Motorcycle): ");
            InputValidator.validateVehicleType(type);

            List<Vehicle> vehicles = service.filterByType(type);
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles found.");
                return;
            }

            System.out.println("--- " + type + " Vehicles ---");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        } catch (InvalidInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    private static void showOwnerHistory() {
        String owner = readLine("Enter owner name: ");
        if (owner.isEmpty()) {
            System.out.println("Owner name cannot be empty.");
            return;
        }

        List<Vehicle> vehicles = service.getVehiclesByOwner(owner);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("--- Vehicles Owned by " + owner + " ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private static void showExpiredRegistrations() {
        int currentYear = LocalDate.now().getYear();
        List<Vehicle> vehicles = service.getExpiredRegistrations(currentYear);
        if (vehicles.isEmpty()) {
            System.out.println("No expired registrations found.");
            return;
        }

        System.out.println("--- Expired Registrations ---");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static void loadSampleData() {
        service.registerVehicle(new Car("CAR-001", "Ahmed Ali", 2019, "EXPIRED", 4));
        service.registerVehicle(new Car("CAR-002", "Sara Kamel", 2023, "ACTIVE", 2));
        service.registerVehicle(new Truck("TRK-001", "Mohamed Said", 2021, "ACTIVE", 10.0));
        service.registerVehicle(new Truck("TRK-002", "Laila Nour", 2017, "EXPIRED", 5.5));
        service.registerVehicle(new Motorcycle("MOT-001", "Omar Fathi", 2022, "ACTIVE", "Sport"));
        service.registerVehicle(new Motorcycle("MOT-002", "Nadia Hamed", 2024, "ACTIVE", "Cruiser"));
    }
}
